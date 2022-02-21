package com.api.sns.naver;

import lombok.Data;

@Data
public class NaverAccess {
    private String access_token;
    private String refresh_token;
    private String token_type;
    private String expires_in;
    private String result;
}
