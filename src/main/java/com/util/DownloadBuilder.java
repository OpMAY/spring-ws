package com.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;

@Slf4j
public class DownloadBuilder {
    private File file;
    private HttpServletResponse response;
    private FileInputStream fileInputStream;
    private ServletOutputStream fileOutputStream;
    private byte[] byteStream;
    private boolean logging = true;

    public DownloadBuilder() {

    }

    public DownloadBuilder init(HttpServletResponse response, boolean logging) {
        this.response = response;
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

    public DownloadBuilder setResponseProperty(HashMap<String, Object> properties) {
        properties.forEach((key, value) -> {
            this.response.setHeader(key, String.valueOf(value));
        });
        return this;
    }

    public boolean filePush() throws IOException {
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
        } catch (FileNotFoundException e) {
            if (logging) {
                log.info("file download failed : " + e.getMessage());
            }
            return false;
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
