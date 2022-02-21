package com.model.common;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public abstract class TimeRecordable {

    private LocalDateTime reg_datetime;
    private LocalDateTime updated_datetime;
}
