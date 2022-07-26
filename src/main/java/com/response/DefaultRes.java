package com.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class DefaultRes<T> {

    private int status;
    private T data;

    public static <T> DefaultRes<T> res(final int status) {
        return DefaultRes.<T>builder()
                .status(status)
                .build();
    }

    public static DefaultRes<Object> res(final int status, Message message) {
        return DefaultRes.builder()
                .data(message.getHashMap())
                .status(status)
                .build();
    }

    public static DefaultRes<Object> res(final int status, Message message, boolean isLog) {
        return DefaultRes.builder()
                .data(message.getHashMap(isLog))
                .status(status)
                .build();
    }
}