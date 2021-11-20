package com.model.sns;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Data
public class Kakao {
    /**Login & OAuth 2.0*/
    private String grant_type = "authorization_code";
    @NonNull
    private String client_id;
    private String token_request = "https://kauth.kakao.com/oauth/token";
    private String user_request = "https://kapi.kakao.com/v2/user/me";
    private String logout_request = "https://kapi.kakao.com/v1/user/logout";
    private boolean isOutput = true;

    /**Common*/
    private String type = "POST";

    /**Kakao pay*/
    @NonNull
    private String app_admin_key;
    private String pay_host = "https://kapi.kakao.com";
    private String pay_host_end_point_ready = "/v1/payment/ready";
    private String pay_host_end_point_approve = "/v1/payment/approve";
    private String pay_host_end_point_cancel = "/v1/payment/cancel";
}
