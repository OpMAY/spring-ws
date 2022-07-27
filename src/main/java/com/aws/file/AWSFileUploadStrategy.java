package com.aws.file;

import com.aws.CDNService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.HashMap;

@Slf4j
@Component
@Qualifier("AWSFileUploadStrategy")
public class AWSFileUploadStrategy implements FileUploadStrategy {

    @Value("${S3}")
    private String aws_domain;

    private final CDNService cdnService;

    public AWSFileUploadStrategy(CDNService cdnService) {
        this.cdnService = cdnService;
    }

    @Override
    public String getFileName(String name, String cdn_path, File file) {
        boolean check = cdnService.upload(cdn_path + name, file);
        if (check) {
            log.info("File AWS Upload Success");
        } else {
            log.info("File AWS Upload Failed");
        }
        return aws_domain + cdn_path + name;
    }
}
