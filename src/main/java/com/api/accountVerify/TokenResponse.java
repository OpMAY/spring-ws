package com.api.accountVerify;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenResponse {
    private String access_token;
    private String token_type;
    private Long expires_in;
    private String scope;
    private String client_use_code;

    private String account_num;
    private String bank_type;
    private String birth_date;

}
