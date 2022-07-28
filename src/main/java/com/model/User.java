package com.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.model.common.MFile;
import com.model.common.Time;
import com.model.jwt.RootUser;
import lombok.*;

@RequiredArgsConstructor
@Data
@AllArgsConstructor
@JsonIgnoreProperties
//@NoArgsConstructor
@ToString(callSuper = true)
public class User extends RootUser {
    private int no;
    private String email;
    private String id;
    private String name;
    private String grant = "general";
    private String access_token = "setting the password";
    private MFile profile_img;
}
