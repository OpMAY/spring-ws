package com.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@JsonAutoDetect
public class UserTest {
    private int no;
    private String email;
    private String id;
    private String name;
    private String grant = "general";
    private String access_token = "setting the password";
}
