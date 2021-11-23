package com.model;

import lombok.Data;

@Data
public class UserTest {
    private int no;
    private String email;
    private String id;
    private String name;
    private String grant = "general";
    private String access_token = "setting the password";
}
