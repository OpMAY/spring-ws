package com.util;

import java.io.File;

public interface FileUploadStrategy {

    String getFileName(String name, String cdn_path, File file);
}
