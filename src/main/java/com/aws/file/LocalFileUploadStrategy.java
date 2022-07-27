package com.aws.file;

import com.aws.CDNService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.HashMap;

@Component
@Slf4j
@Qualifier("LocalFileUploadStrategy")
public class LocalFileUploadStrategy implements FileUploadStrategy {

    @Value("${DOMAIN}")
    private String domain;

    public LocalFileUploadStrategy() {
    }

    @Override
    public String getFileName(String name, String cdn_path, File file) {
        return domain + "files/" + name;
    }
}
