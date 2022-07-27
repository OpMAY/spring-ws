package com.aws.model;
import lombok.Data;

@Data
public class Download {
    private String created_local_path;
    private String file_path;
    private String file_name;
}
