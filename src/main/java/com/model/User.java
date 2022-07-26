package com.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.model.common.Time;
import com.model.jwt.RootUser;
import lombok.*;

@RequiredArgsConstructor
@Data
@AllArgsConstructor
@JsonIgnoreProperties
@NoArgsConstructor
@ToString(callSuper = true)
public class User extends RootUser {
    private int no;
    @NonNull
    private String email;
    @NonNull
    private String id;
    @NonNull
    private String name;
    private String grant = "general";
    private String access_token = "setting the password";
}
