package com.model.queue;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
@AllArgsConstructor
public class TokenTime {
    private LocalDateTime reg_datetime;
    private LocalDateTime update_datetime;
}
