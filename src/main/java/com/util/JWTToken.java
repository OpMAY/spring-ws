package com.util;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class JWTToken {
    private String id;
    private String signature;
    private String grant;
    private String version;
    private String type;

    public JWTToken() {
    }
}
