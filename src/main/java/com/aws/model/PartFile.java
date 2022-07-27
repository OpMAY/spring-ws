package com.aws.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartFile {
    private String data;
    private String file_name;
    private Integer part_index;
    private String upload_id;
    private String partETags;
}