package com.api.lunarsoft.alarm;

import lombok.Data;

@Data
public class ResMessage {
    private String no;
    private String tel_num;
    private int result_code;
    private String result_msg;
}
