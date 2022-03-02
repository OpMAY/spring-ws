package com.util;

import com.model.common.MFile;
import com.util.fileUploadStrategy.FileUploadStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class FileUploadUtility {

    @Value("${UPLOAD_PATH}")
    private String upload_path;

    private final FileUploadStrategy fileUploadStrategy;

    @Autowired // localFileUploadStrategy or AWSFileUploadStrategy
    public FileUploadUtility(@Qualifier("localFileUploadStrategy") FileUploadStrategy fileUploadStrategy) {
        this.fileUploadStrategy = fileUploadStrategy;
    }

    public MFile uploadFile(MultipartFile file, String cdn_path) {
        if (file == null || file.getSize() == 0) {
            return null;
        }
        try {
            Folder.mkdirs(upload_path);
            String saved_name = UUID.randomUUID() + "_" + file.getOriginalFilename();
//            String saved_name = UUID.randomUUID().toString().substring(0, 8) + "_" + file.getOriginalFilename();
            File target = new File(upload_path, saved_name);
            FileCopyUtils.copy(file.getBytes(), target);

            saved_name = fileUploadStrategy.getFileName(saved_name, cdn_path, target);

            return new MFile(file, saved_name);
        } catch (IOException e) {
            log.error("ex : IOException");
            return null;
        }
    }

    public List<MFile> uploadFiles(List<MultipartFile> files, String cdn_path) {
        List<MFile> mFiles = new ArrayList<>();

        if (files == null) return mFiles;

        for (MultipartFile file : files) {
            MFile mFile = uploadFile(file, cdn_path);
            if (mFile != null) {
                mFiles.add(mFile);
            }
        }
        return mFiles;
    }

    public List<MFile> uploadFiles(MultipartFile[] files, String cdn_path) {
        if (files == null) return new ArrayList<>();

        return uploadFiles(new ArrayList<>(Arrays.asList(files)), cdn_path);
    }
}
