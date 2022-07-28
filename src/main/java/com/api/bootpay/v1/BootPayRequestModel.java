package com.api.bootpay.v1;

import lombok.Data;

import java.util.List;

@Data
public class BootPayRequestModel {
    private int price;
    private int tax_free = 0;
    private String application_id;
    private String name = "LE CHAT 375ml / 12.7fl.oz";
    private String pg = "nicepay";
    // Only for kakao
    private String method = "kakao";
    private boolean show_agree_window = false;
    private List<Item> items;
    private BootpayUser user_info;
    private String order_id;
}
