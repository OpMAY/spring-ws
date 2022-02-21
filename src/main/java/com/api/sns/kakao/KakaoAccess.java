package com.api.sns.kakao;

import lombok.Data;

@Data
public class KakaoAccess {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private String refresh_token_expires_in;
    private String scope;
    private String expires_in;
}