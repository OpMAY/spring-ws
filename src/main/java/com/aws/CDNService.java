package com.aws;

import com.amazonaws.AmazonClientException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.aws.model.AWSModel;
import com.aws.model.smodel.Download;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class CDNService {

    private final AWSModel pathModel;
    private AmazonS3 s3Client;

    @Autowired
    public CDNService(AWSModel pathModel) {
        this.pathModel = pathModel;

        try {
            AWSCredentials credentials = new BasicAWSCredentials(pathModel.getAccessKey(), pathModel.getSecretKey());
            this.s3Client = AmazonS3ClientBuilder
                    .standard()
                    .withCredentials(new AWSStaticCredentialsProvider(credentials))
                    .withRegion(Regions.AP_NORTHEAST_2)
                    .build();
            if (!this.s3Client.doesBucketExistV2(pathModel.getBucketName())) {
                // Verify that the bucket was created by retrieving it and checking its location.
                this.s3Client.createBucket(pathModel.getBucketName());
                this.s3Client.getBucketLocation(new GetBucketLocationRequest(pathModel.getBucketName()));
            }
        } catch (AmazonClientException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it and returned an error response.
            e.printStackTrace();
        }
    }

    /**
     * @param cdn_full_path Upload file path + file name
     * @param file          file to upload
     * @return true / false
     */
    public boolean upload(String cdn_full_path, File file) {
        try {
            s3Client.putObject(pathModel.getBucketName(), cdn_full_path, file);
            return true;
        } catch (SdkClientException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param download Download file
     * @return File or Null
     */
    public File download(Download download) {
        try {
            File file = new File(download.getCreated_local_path() + download.getCdn_fname());
            S3Object s3object = s3Client.getObject(pathModel.getBucketName(), download.getCdn_fpath() + "/" + download.getCdn_fname());
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

    private ArrayList<String> getBucketFiles() {
        ListObjectsV2Result result = s3Client.listObjectsV2(pathModel.getBucketName());
        List<S3ObjectSummary> objs = result.getObjectSummaries();

        ArrayList<String> files = new ArrayList<>();
        objs.forEach(obj -> files.add(obj.getKey()));
        return files;
    }

    /**
     * AWS Buffer Upload Test
     */
    public void awsBufferUploadTest(String path, String file_name) {
        File file = new File(path + file_name);
        try (InputStream inputStream = new FileInputStream(file)) {
            log.info("aws upload file name : " + file.getName());
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            metadata.setContentLength(file.length());
            PutObjectRequest putObjectRequest = new PutObjectRequest(pathModel.getBucketName(), "bulk/test/Nature - 105936.mp4", inputStream, metadata);
            s3Client.putObject(putObjectRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
