package com.api.bootpay.v2;

import lombok.Data;

@Data
public class BootPayUserV2 {
    private String id;
    private String username;
    private String phone;
    private String email;
    private String addr;
}
