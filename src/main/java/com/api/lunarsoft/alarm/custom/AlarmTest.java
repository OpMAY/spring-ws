package com.api.lunarsoft.alarm.custom;

import com.api.lunarsoft.alarm.Alarm;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


/**
 * PR
 *
 * @Date 2022-07-27
 * @Author kimwoosik
 * AlarmTest alarmTest = AlarmTest.builder()
 *                 .shop_name("")
 *                 .url("")
 *                 .tel_no("")
 *                 .send_phone("")
 *                 .template_id(0)
 *                 .user_name("")
 *                 .user_id("")
 *                 .build();
 * */

@Getter
@ToString(callSuper = true)
public class AlarmTest extends Alarm {
    private int template_id = 26077;
    private String user_name;
    private String user_id;

    @Builder
    public AlarmTest(String shop_name, String url, String tel_no, String send_phone, int template_id, String user_name, String user_id) {
        super(shop_name, url, tel_no, send_phone);
        this.template_id = template_id;
        this.user_name = user_name;
        this.user_id = user_id;
    }
}
