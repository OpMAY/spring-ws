package com.api.lunarsoft.alarm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public abstract class Alarm {
    private String shop_name = "shop_name";
    private String url = "host_url";
    /**
     * Tel은 01045299453 (개행없이 전개)
     */
    private String tel_no;
    private String send_phone = "phone";
}
