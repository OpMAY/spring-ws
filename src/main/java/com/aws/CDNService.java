package com.aws;
/*
 * Created By zlzld in SpringMavenProject
 * Date : 1월 월요일 16 10
 */

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.aws.model.AWSModel;
import lombok.extern.log4j.Log4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class CDNService {

    private AWSModel pathModel;

    private AmazonS3 s3Client;

    public CDNService(String accessKey, String secretKey, String bucketName) {
        pathModel = new AWSModel();
        pathModel.setAccessKey(accessKey);
        pathModel.setSecretKey(secretKey);
        pathModel.setBucketName(bucketName);
        try {
            AWSCredentials credentials = new BasicAWSCredentials(pathModel.getAccessKey(), pathModel.getSecretKey());
            s3Client = AmazonS3ClientBuilder.standard()
                    .withCredentials(new AWSStaticCredentialsProvider(credentials))
                    .withRegion(Regions.AP_NORTHEAST_2)
                    .build();
            if (!s3Client.doesBucketExistV2(pathModel.getBucketName())) {
                // Verify that the bucket was created by retrieving it and checking its location.
                s3Client.createBucket(pathModel.getBucketName());
                s3Client.getBucketLocation(new GetBucketLocationRequest(pathModel.getBucketName()));
            }
        } catch (AmazonClientException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it and returned an error response.
            e.printStackTrace();
        }
    }

    /**
     * @param awsModel : AWSModel(Upload)
     * @return boolean : success = true, failed = false
     */
    public boolean upload(AWSModel awsModel) {
        try {
            String awsCdnFilePath = awsModel.getUpload().getCdn_fpath();
            s3Client.putObject(pathModel.getBucketName(), awsCdnFilePath, awsModel.getUpload().getFile());
            return true;
        } catch (AmazonServiceException e) {
            e.printStackTrace();
            return false;
        } catch (SdkClientException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param awsModel : AWSModel(Download)
     * @return File or Null
     */
    public File download(AWSModel awsModel) {
        try {
            File file = new File(awsModel.getDownload().getCreated_local_path() + awsModel.getDownload().getCdn_fname());
            S3Object s3object = s3Client.getObject(pathModel.getBucketName(), awsModel.getDownload().getCdn_fpath() + "/" + awsModel.getDownload().getCdn_fname());
            S3ObjectInputStream inputStream = s3object.getObjectContent();
            FileUtils.copyInputStreamToFile(inputStream, file);  //#2 - 스트림을 파일로 저장함
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean delete(String awsCdnFilePath) {
        s3Client.deleteObject(new DeleteObjectRequest(pathModel.getBucketName(), awsCdnFilePath));
        return true;
    }

    public boolean deletes(String[] awsCdnFilePaths) {
        DeleteObjectsRequest delObjReq = new DeleteObjectsRequest(pathModel.getBucketName()).withKeys(awsCdnFilePaths);
        s3Client.deleteObjects(delObjReq);
        return true;
    }

    public ArrayList<String> getBucketFiles(String bucketName) {
        ArrayList<String> files = new ArrayList<>();
        ListObjectsV2Result result = s3Client.listObjectsV2(bucketName);
        List<S3ObjectSummary> objs = result.getObjectSummaries();
        for (S3ObjectSummary obj : objs) {
            files.add(obj.getKey());
        }
        return files;
    }
}
