package com.api.bootpay.v2;

import lombok.Data;

@Data
public class BootPayV2 {
    private String receipt_id;
    private String order_id;
    private int price;
    private int tax_free;
    private int cancelled_price;
    private int cancelled_tax_free;
    private String order_name;
    private String company_name;
    private String gateway_url;
    private boolean sandbox;
    private String pg;
    private String method;
    private String method_symbol;
    private String method_origin_symbol;
    private String purchased_at;
    private String cancelled_at;
    private String requested_at;
    private String receipt_url;
    private int status;
    private CardData cardData;
}
