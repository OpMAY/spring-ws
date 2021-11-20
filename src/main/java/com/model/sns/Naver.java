package com.model.sns;

import lombok.Data;
import lombok.NonNull;

@Data
public class Naver {
    private String grant_type = "authorization_code";
    private String grant_delete_type = "delete";
    @NonNull
    private String client_id;
    @NonNull
    private String clientSecret;
    private String service_provider = "NAVER";
}
