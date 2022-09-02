package com.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.model.common.MFile;
import com.model.common.Time;
import com.model.grant.GRANT_TYPE;
import com.model.jwt.RootUser;
import lombok.*;

@RequiredArgsConstructor
@Data
@AllArgsConstructor
@JsonIgnoreProperties
@ToString(callSuper = true)
public class User extends RootUser {
    private int no;
    private String email;
    private String id;
    private String name;
    private GRANT_TYPE grant = GRANT_TYPE.USER;
    private String access_token = "setting the password";
    private MFile profile_img;
}
