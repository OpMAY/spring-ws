package com.aws.model.smodel;

import lombok.Data;

import java.io.File;

@Data
public class Upload {
    private String cdn_fpath;
    private File file;

    public Upload(String cdn_fpath, File file) {
        this.cdn_fpath = cdn_fpath;
        this.file = file;
    }
}
