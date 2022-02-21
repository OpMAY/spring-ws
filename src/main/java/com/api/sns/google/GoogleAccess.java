package com.api.sns.google;

import lombok.Data;

@Data
public class GoogleAccess {
    private String access_token;
    private String expires_in;
    private String scope;
    private String token_type;
    private String id_token;
}
