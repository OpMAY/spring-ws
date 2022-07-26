package com.api.lunarsoft.alarm;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

/**
 * PR
 *
 * @Date 2022-07-27
 * @Author kimwoosik
 *
 *         AlarmTemplate alarmTemplate = AlarmTemplate
 *                 .builder()
 *                 .template_id(alarm.getTemplate_id())
 *                 .userid(CLIENT_ID)
 *                 .api_key(CLIENT_SECRET)
 *                 .build();
 *         alarmTemplate.messageInitialize("1", "1",
 *                 "http://localhost:8080",
 *                 alarm.getTel_no(), "", "");
 * */

@Getter
@Setter
@ToString
public class AlarmTemplate {
    private String userid;
    private String api_key;
    private int template_id;
    private ArrayList<Message> messages;

    @Builder
    public AlarmTemplate(String userid, String api_key, int template_id, ArrayList<Message> messages) {
        this.userid = userid;
        this.api_key = api_key;
        this.template_id = template_id;
        this.messages = messages;
    }

    public AlarmTemplate messageInitialize(String no, String useSms, String buttonUrl, String telNum, String msg_content, String sms_content) {
        this.messages = new ArrayList<>();
        ArrayList<ButtonURL> buttonURLS = new ArrayList<>();
        buttonURLS.add(new ButtonURL(buttonUrl, buttonUrl));
        Message message = Message.builder()
                .no(no)
                .tel_num(telNum)
                .msg_content(msg_content)
                .sms_content(sms_content)
                .use_sms(useSms)
                .btn_url(buttonURLS)
                .build();
        this.messages.add(message);
        return this;
    }
}
