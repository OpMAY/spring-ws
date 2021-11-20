package com.exception.enums;

public interface BaseExceptionType {
    int getErrorCode();

    int getHttpStatus();

    String getErrorMessage();
}