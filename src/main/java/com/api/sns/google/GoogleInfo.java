package com.api.sns.google;

import lombok.Data;

@Data
public class GoogleInfo {
    private String id;
    private String email;
    private boolean verified_email;
    private String picture;
    private String name;
}
