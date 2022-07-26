package com.model.common;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MFile {

    private String url;
    private String name;
    private long size;
    private String type;

    public MFile() {
    }

    public MFile(MultipartFile multipartFile) {
        this.size = multipartFile.getSize();
        this.name = multipartFile.getOriginalFilename();
        this.type = multipartFile.getContentType();
    }

    public MFile(MultipartFile multipartFile, String url) {
        this.size = multipartFile.getSize();
        this.name = multipartFile.getOriginalFilename();
        this.type = multipartFile.getContentType();
        this.url = url;
    }
}
