package com.model;

import lombok.Data;


@Data

public class Mail {
    private String host_email;
    private String host_password;
    private String host_address;
    private int host_port;
    private String host_auth;

    private String received_email;
    private String title;
    private String contents;
    private String phone;
    private String name;

    public Mail() {
        /*Company Owner*/
        this.host_email = "okiwicorp@naver.com"; //2차 인증을 사용하지 않는 계정이여야 합니다.
        this.host_password = "dhzldnl1!";
        this.host_address = "smtp.naver.com";
        this.host_port = 587;
        this.host_auth = "true";
    }
}
