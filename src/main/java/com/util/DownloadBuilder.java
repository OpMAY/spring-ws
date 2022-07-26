package com.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
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
    private boolean logging = true;
    private String path = "C:/Users/sangwoo/Desktop/spring-a.2/out/artifacts/webapplication_Web_exploded/files/";

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

    public DownloadBuilder header() throws UnsupportedEncodingException {
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file.getName(), "UTF-8")); // 다운로드 되거나 로컬에 저장되는 용도로 쓰이는지를 알려주는 헤더
        properties.forEach((key, value) -> this.response.setHeader(key, String.valueOf(value)));
        return this;
    }
}
