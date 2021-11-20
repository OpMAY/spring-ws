package com.aws.model;

import com.aws.model.smodel.Download;
import com.aws.model.smodel.Upload;
import lombok.Data;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

@Log4j
@Data
public class AWSModel {
    private Upload upload;
    private Download download;
    private String accessKey;
    private String secretKey;
    private String bucketName;
}
