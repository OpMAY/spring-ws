package com.api.lunarsoft.alarm;

import lombok.*;

import java.util.ArrayList;

@ToString
@Getter
@Setter
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

    @Builder
    public Message(String no, String tel_num, String reserve_time, String custom_key, String msg_content, String sms_content, int price, String currency_code, String carrier_code, String invoice_number, String use_sms, String app_user_id, ArrayList<ButtonURL> btn_url) {
        this.no = no;
        this.tel_num = tel_num;
        this.reserve_time = reserve_time;
        this.custom_key = custom_key;
        this.msg_content = msg_content;
        this.sms_content = sms_content;
        this.price = price;
        this.currency_code = currency_code;
        this.carrier_code = carrier_code;
        this.invoice_number = invoice_number;
        this.use_sms = use_sms;
        this.app_user_id = app_user_id;
        this.btn_url = btn_url;
    }
}
