package com.model.sns;

import lombok.Data;
import lombok.NonNull;

@Data
public class Naver {
    private String grant_type = "authorization_code";
    private String grant_delete_type = "delete";
    private String client_id;
    private String clientSecret;
    private String service_provider = "NAVER";

    public Naver(String client_id, String client_secret) {
        this.client_id = client_id;
        this.clientSecret = client_secret;
    }
}
