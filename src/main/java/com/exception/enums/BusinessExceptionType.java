package com.exception.enums;

public enum BusinessExceptionType implements BaseExceptionType {
    GRANT_EXCEPTION(1001, 200, "1001, You do not have permission.");

    private int errorCode;
    private int httpStatus;
    private String errorMessage;

    BusinessExceptionType(int errorCode, int httpStatus, String errorMessage) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

    @Override
    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public int getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
