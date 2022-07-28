package com.api.bootpay.v1;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentData {
    private String card_name;
    private String card_no;
    private String card_quota;
    private String card_code;
    private String card_auth_no;
    private String receipt_id;
    private String n;
    private int p;
    private String tid;
    private String pg;
    private String pm;
    private String pg_a;
    private String pm_a;
    private String o_id;
    private String p_at;
    private int s;
    private int g;
}
