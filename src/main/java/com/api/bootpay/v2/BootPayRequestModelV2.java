package com.api.bootpay.v2;

import lombok.Data;

import java.util.List;

/**
 * BootPayRequestModel For BootPay v2
 * @Author : 한지우
 * CreateDate : 2022-07-25
 * **/
@Data
public class BootPayRequestModelV2 {
    private String application_id;
    private int price;
    private String order_name;
    private String order_id;
    private String pg;
    private String method;
    private int tax_free;
    private BootPayUserV2 user;
    private List<BootPayItem> items;
    private Extra extra;
}
