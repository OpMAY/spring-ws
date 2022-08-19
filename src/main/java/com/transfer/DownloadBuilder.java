package com.transfer;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

@Slf4j
public class DownloadBuilder {
    private File file;
    private HttpServletResponse response;
    private FileInputStream fileInputStream;
    private ServletOutputStream fileOutputStream;
    private byte[] byteStream;
    private boolean logging = false;

    public DownloadBuilder() {

    }

    public DownloadBuilder init(HttpServletResponse response, boolean logging) {
        this.response = response;
        if (logging) {
            this.logging = true;
        }
        return this;
    }

    public DownloadBuilder file(File file) {
        this.file = file;
        if (this.logging) {
            log.info("{},{},{}", "file info : ", file.getName(), file.getPath());
        }
        return this;
    }

    public DownloadBuilder file(byte[] file) {
        this.byteStream = file;
        return this;
    }

    public DownloadBuilder header() throws UnsupportedEncodingException {
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("Content-Disposition", "attachment;filename=" + URLEncoder.encode(this.file.getName(), "UTF-8")); // 다운로드 되거나 로컬에 저장되는 용도로 쓰이는지를 알려주는 헤더
        setResponseProperty(properties);
        return this;
    }

    public DownloadBuilder setResponseProperty(HashMap<String, Object> properties) {
        properties.forEach((key, value) -> {
            this.response.setHeader(key, String.valueOf(value));
        });
        return this;
    }

    public boolean send() throws IOException {
        try {
            if (this.fileInputStream != null) {
                this.fileInputStream.close();
            }
            this.fileInputStream = new FileInputStream(this.file);
            this.fileOutputStream = this.response.getOutputStream();
            int read = 0;
            byte[] buffer = new byte[1024];
            while ((read = fileInputStream.read(buffer)) != -1) { // 1024바이트씩 계속 읽으면서 outputStream에 저장, -1이 나오면 더이상 읽을 파일이 없음
                this.fileOutputStream.write(buffer, 0, read);
            }
            if (logging) {
                log.info("file download success");
            }
            return true;
        } catch (IOException e) {
            if (logging) {
                log.info("file download failed : " + e.getMessage());
            }
            return false;
        } finally {
            if (this.fileInputStream != null) {
                this.fileInputStream.close();
                if (logging) {
                    log.info("FileInputStream is closed");
                }
            }
            if (this.fileOutputStream != null) {
                this.fileOutputStream.close();
                if (logging) {
                    log.info("FileOutputStream is closed");
                }
            }
        }
    }
}
