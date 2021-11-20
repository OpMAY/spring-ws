package com.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@Data
public class TestModel {
    private String str_default = "str_default";
    private int int_default = 1;
}
