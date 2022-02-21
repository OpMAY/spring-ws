package com.api.lunarsoft.alarm;

import lombok.Data;

@Data
public abstract class Alarm {
    private String shop_name = "shop_name";
    private String url = "host_url";
    /**
     * Tel은 01045299453 (개행없이 전개)
     */
    private String tel_no;
    private String send_phone = "phone";
}
