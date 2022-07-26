package com.model.common;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public abstract class FilesUploadable extends Time {

    private List<MFile> files;
}
