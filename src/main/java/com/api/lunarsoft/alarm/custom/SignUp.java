package com.api.lunarsoft.alarm.custom;

import com.api.lunarsoft.alarm.Alarm;
import lombok.Data;

@Data
public class SignUp extends Alarm {
    @Override
    public String getShop_name() {
        return super.getShop_name();
    }

    @Override
    public String getUrl() {
        return super.getUrl();
    }

    @Override
    public String getTel_no() {
        return super.getTel_no();
    }

    @Override
    public String getSend_phone() {
        return super.getSend_phone();
    }

    @Override
    public void setShop_name(String shop_name) {
        super.setShop_name(shop_name);
    }

    @Override
    public void setUrl(String url) {
        super.setUrl(url);
    }

    @Override
    public void setTel_no(String tel_no) {
        super.setTel_no(tel_no);
    }

    @Override
    public void setSend_phone(String send_phone) {
        super.setSend_phone(send_phone);
    }

    private int template_id = 26077;
    private String user_name;
    private String user_id;
}
