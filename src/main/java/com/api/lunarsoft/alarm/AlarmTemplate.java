package com.api.lunarsoft.alarm;

import lombok.Data;

import java.util.ArrayList;

@Data
public class AlarmTemplate {
    private String userid;
    private String api_key;
    private int template_id;
    private ArrayList<Message> messages;
}
