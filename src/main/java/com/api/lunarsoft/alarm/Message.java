package com.api.lunarsoft.alarm;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Message {
    private String no;
    private String tel_num;
    private String reserve_time;
    private String custom_key;
    private String msg_content;
    private String sms_content;
    private int price=1000;
    private String currency_code = "KRW";
    private String carrier_code;
    private String invoice_number;
    private String use_sms = "1";
    private String app_user_id;
    private ArrayList<ButtonURL> btn_url;
}
