package com.aws.model.smodel;
import lombok.Data;

@Data
public class Download {
    private String created_local_path;
    private String cdn_fpath;
    private String cdn_fname;
}
