package com.api.lunarsoft.alarm;

import lombok.Data;

@Data
public class ButtonURL {
    private String url_pc = "http://localhost:8080";
    private String url_mobile = "http://localhost:8080";
    private String scheme_android;
    private String scheme_ios;

    public ButtonURL(String url_pc, String url_mobile) {
        this.url_pc = url_pc;
        this.url_mobile = url_mobile;
    }
}
