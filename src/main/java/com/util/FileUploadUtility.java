package com.util;

import com.aws.CDNService;
import com.aws.model.AWSModel;
import com.aws.model.smodel.Upload;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Log4j
@Service
@PropertySources({
        @PropertySource("classpath:aws.properties"),
        @PropertySource("classpath:path.properties")
})
public class FileUploadUtility {
    @Value("${filepath}")
    private String upload_path;
    @Value("${AWSModel.accessKey}")
    private String accessKey;
    @Value("${AWSModel.secretKey}")
    private String secretKey;
    @Value("${AWSModel.bucketName}")
    private String bucketName;

    /**
     * @param cdn_dir_path cdn upload path
     * @param fileDate     file byte[] data
     * @param originalName file name
     * @param type         file upload type(AWS or LOCAL)
     */
    public String uploadFile(String cdn_dir_path, String originalName, byte[] fileDate, String type) throws IOException {
        UUID uid = UUID.randomUUID();
        String savedName = uid.toString() + "_" + originalName;
        File target = new File(upload_path, savedName);
        log.info("Upload : " + target.getAbsolutePath());
        if (type.equals(Constant.LOCAL_SAVE)) {
            FileCopyUtils.copy(fileDate, target);
            //org.springframework.util 패키지의 FileCopyUtils는 파일 데이터를 파일로 처리하거나, 복사하는 등의 기능이 있다.
        } else if (type.equals(Constant.AWS_SAVE)) {
            FileCopyUtils.copy(fileDate, target);
            CDNService cdnService = new CDNService(accessKey, secretKey, bucketName);
            AWSModel awsModel = new AWSModel();
            awsModel.setUpload(new Upload(cdn_dir_path, target));
            boolean check = cdnService.upload(awsModel);
            if (check) {
                log.info("File AWS Upload Success");
            } else {
                log.info("File AWS Upload Failed");
            }
        } else {
            log.info("other case");
        }
        return savedName;
    }

    /**
     * @param rebase_position_WHX : W:넓이중심, H:높이중심, X:설정한 수치로(비율무시)
     * @param original_path       : 원본 파일
     * @param resize_target_path  : Resize 되는 타겟 파일
     * @param format              : 원본 파일 format
     * @return String : resize_target_path
     */
    public String resize(String original_path, String resize_target_path, String format, int width, int height, String rebase_position_WHX) {
        try {
            Image image;
            int imageWidth;
            int imageHeight;
            double ratio;
            int w;
            int h;
            // 원본 이미지 가져오기
            image = ImageIO.read(new File(original_path));

            // 원본 이미지 사이즈 가져오기
            imageWidth = image.getWidth(null);
            imageHeight = image.getHeight(null);

            if (rebase_position_WHX.equals("W")) {    // 넓이기준
                ratio = (double) width / (double) imageWidth;
                w = (int) (imageWidth * ratio);
                h = (int) (imageHeight * ratio);
            } else if (rebase_position_WHX.equals("H")) { // 높이기준
                ratio = (double) height / (double) imageHeight;
                w = (int) (imageWidth * ratio);
                h = (int) (imageHeight * ratio);
            } else { //설정값 (비율무시)
                w = width;
                h = height;
            }

            // 이미지 리사이즈
            // Image.SCALE_DEFAULT : 기본 이미지 스케일링 알고리즘 사용
            // Image.SCALE_FAST    : 이미지 부드러움보다 속도 우선
            // Image.SCALE_REPLICATE : ReplicateScaleFilter 클래스로 구체화 된 이미지 크기 조절 알고리즘
            // Image.SCALE_SMOOTH  : 속도보다 이미지 부드러움을 우선
            // Image.SCALE_AREA_AVERAGING  : 평균 알고리즘 사용
            Image resizeImage = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);

            // 새 이미지  저장하기
            BufferedImage newImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            Graphics g = newImage.getGraphics();
            g.drawImage(resizeImage, 0, 0, null);
            g.dispose();
            ImageIO.write(newImage, format, new File(resize_target_path));
            return resize_target_path;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
