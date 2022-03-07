package com.model.mybatis;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ArrayTestModel {
    private int no;
    private String name;
    private ArrayList<String> tags;
}
