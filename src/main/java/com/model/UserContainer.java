package com.model;

import lombok.Data;

import java.util.Map;

@Data
public class UserContainer {
    private int no;
    private Map<String, User> user;
}
