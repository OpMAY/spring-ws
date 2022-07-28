package com.api.bootpay.v1;

import lombok.Getter;

@Getter
public class Cancel {
    private String receipt_id;
    private String cancel_username;
    private String cancel_reason;
    private Integer cancel_price;

    public Cancel() {
    }

    public Cancel(String receipt_id) {
        this.receipt_id = receipt_id;
    }

    public Cancel(String receipt_id, String cancel_username, String cancel_reason) {
        this.receipt_id = receipt_id;
        this.cancel_username = cancel_username;
        this.cancel_reason = cancel_reason;
    }

    public Cancel(String receipt_id, String cancel_username, String cancel_reason, Integer cancel_price) {
        this.receipt_id = receipt_id;
        this.cancel_username = cancel_username;
        this.cancel_reason = cancel_reason;
        this.cancel_price = cancel_price;
    }


}
