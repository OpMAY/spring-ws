package com.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Data
@Getter
@Setter
@JsonAutoDetect
public class ArrayTest {
    private int no;
    private ArrayList<UserTest> userTests;
}
