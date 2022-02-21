package com.model.common;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public abstract class TimeRecordable {

    public LocalDateTime reg_datetime;
    public LocalDateTime updated_datetime;
}
