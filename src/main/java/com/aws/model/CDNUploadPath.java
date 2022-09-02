package com.aws.model;

public enum CDNUploadPath {
    TEST("test/"),
    BANNER("admin/banner/"),
    ADMIN("admin/"),
    USER("user/");

    private final String path;

    CDNUploadPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
