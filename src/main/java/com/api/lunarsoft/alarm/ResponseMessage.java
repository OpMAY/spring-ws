package com.api.lunarsoft.alarm;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ResponseMessage {
    private String code;
    private String msg;
    private String userid;
    private String requested_at;
    private ArrayList<ResMessage> messages;
}
