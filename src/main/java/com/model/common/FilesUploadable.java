package com.model.common;

import lombok.Getter;

import java.util.List;

@Getter
public abstract class FilesUploadable extends TimeRecordable {

    private List<MFile> files;
}
