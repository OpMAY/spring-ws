package com.api.bootpay.v2;

import lombok.Data;

@Data
public class CardData {
    private String tid;
    private String card_approve_no;
    private String card_no;
    private String card_quota;
    private String card_company_code;
    private String card_company;
    private String card_interest;
    private String card_type;
    private String receipt_url;
    private String card_owner_type;
    private int point;
}
