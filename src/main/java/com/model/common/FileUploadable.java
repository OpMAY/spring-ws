package com.model.common;

import lombok.Data;
import lombok.Getter;

@Data
public abstract class FileUploadable extends Time {

    private MFile file;
}
