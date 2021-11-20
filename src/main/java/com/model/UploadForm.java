package com.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class UploadForm {
    private String id;
    private String password;
    private MultipartFile file;
    private List<MultipartFile> files;
}
