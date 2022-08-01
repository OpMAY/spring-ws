package com.aws.file;

import com.aws.model.PartFile;
import com.model.common.MFile;
import com.util.Constant;
import com.util.Folder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Slf4j
@Service
public class FileUploadUtility {

    private final String upload_path;

    private final FileUploadStrategy fileUploadStrategy;

    /**
     * PR AWS CDN Service
     *
     * @Date 2022-07-27
     * @Author kimwoosik
     * Strategy 패턴 사용
     * LocalFileUploadStrategy or awsFileUploadStrategy 중 선택
     */
    @Autowired
    public FileUploadUtility(@Qualifier("AWSFileUploadStrategy") FileUploadStrategy fileUploadStrategy,
                             ConfigurableWebApplicationContext ctx) {
        // Property Config 에서 무조건 걸어주기 떄문에 properties 세팅만 잘 되어있다면 오류 발생 X
        upload_path = (String) ctx.getEnvironment().getPropertySources().get("path_props").getProperty("UPLOAD_PATH");
        log.info("FileUploadUtility -> {}", upload_path);
        this.fileUploadStrategy = fileUploadStrategy;
    }

    public MFile uploadFile(MultipartFile file, String cdn_path) {
        try {
            if (file == null || file.getSize() == 0) {
                return null;
            }
            Folder.mkdirs(upload_path);
            String saved_name = UUID.randomUUID() + "_" + file.getOriginalFilename();
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


    static final String SPLIT_STRING = ";base64,";

    public boolean localMultiPartUpload(PartFile part_file_data) {
        Folder.mkdirs(upload_path);
        String file_path = upload_path + part_file_data.getFile_name();

        int start_idx = part_file_data.getData().indexOf(SPLIT_STRING) + SPLIT_STRING.length();
        String target_string = part_file_data.getData().substring(start_idx);
        byte[] decoded_byte = Base64.getDecoder().decode(target_string);

        try (FileOutputStream fos = new FileOutputStream(file_path, true)) {
            fos.write(decoded_byte);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void localMultiPartDelete(PartFile part_file_data) {
        Folder.deleteFile(upload_path + part_file_data.getFile_name());
    }
}
