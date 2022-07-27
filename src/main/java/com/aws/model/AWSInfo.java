package com.aws.model;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class AWSInfo {
    @Value("${AWS.ACCESS}")
    private String accessKey;
    @Value("${AWS.SECRET}")
    private String secretKey;
    @Value("${AWS.BUCKET}")
    private String bucketName;
}
