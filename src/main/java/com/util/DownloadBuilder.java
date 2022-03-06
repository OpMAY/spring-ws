package com.util;

import com.model.SplitFileData;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

@Slf4j
public class DownloadBuilder {
    private File file;
    private HttpServletResponse response;
    private FileInputStream fileInputStream;
    private ServletOutputStream fileOutputStream;
    private byte[] byteStream;
    private boolean logging = true;
    private List<String> read;
    private String path = "C:/Users/sangwoo/Desktop/spring-a.2/out/artifacts/webapplication_Web_exploded/files/";
    private JSONArray jsonArray;

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
    public boolean bulkFilePush(SplitFileData splitFileData, boolean is_last) throws IOException {
        try {
            if (this.fileOutputStream == null) {
                this.fileOutputStream = this.response.getOutputStream();
            }
            this.jsonArray = new JSONArray(splitFileData.getJsonStr());

            for (int i = 0; i < jsonArray.length(); i++) {
                this.read = Files.readAllLines(Paths.get(path + jsonArray.getJSONObject(i).getString("file_name")));
                for (String s : this.read) {
                    this.fileOutputStream.write(Base64.getDecoder().decode(s));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (this.fileOutputStream != null) {
                this.fileOutputStream.flush();
                this.fileOutputStream.close();
            }
            return false;
        } finally {
            if (this.fileOutputStream != null && is_last) { // 마지막 파일일때
                this.fileOutputStream.flush();
                this.fileOutputStream.close();
            }
        }
        return true;
    }

    public DownloadBuilder header() throws UnsupportedEncodingException {
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file.getName(), "UTF-8")); // 다운로드 되거나 로컬에 저장되는 용도로 쓰이는지를 알려주는 헤더
        properties.forEach((key, value) -> this.response.setHeader(key, String.valueOf(value)));
        return this;
    }
}
