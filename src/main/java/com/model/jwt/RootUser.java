package com.model.jwt;

import com.model.common.Time;
import com.model.grant.GRANT_TYPE;
import lombok.Data;

@Data
abstract public class RootUser extends Time {
    private String version;
    private GRANT_TYPE grant;
    private String access_token;
    private String email;
    private String id;
    private int no;
}
