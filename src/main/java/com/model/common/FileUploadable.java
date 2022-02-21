package com.model.common;

import lombok.Getter;

@Getter
public abstract class FileUploadable extends TimeRecordable {

    private MFile file;
}
