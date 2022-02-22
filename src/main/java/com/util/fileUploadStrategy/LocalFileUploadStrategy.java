package com.util.fileUploadStrategy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class LocalFileUploadStrategy implements FileUploadStrategy {

    @Value("${DOMAIN}")
    private String domain;

    @Override
    public String getFileName(String name, String cdn_path, File file) {
        return domain + "files/" + name;
    }
}
