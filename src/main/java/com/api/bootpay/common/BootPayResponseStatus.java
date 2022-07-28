package com.api.bootpay.common;

public enum BootPayResponseStatus {
    PAYED(1),
    CANCELED(20),
    PAY_WAITING(0),
    VIRTUAL_ACCOUNT_CREATED(5),
    APPLY_WAITING(2),
    PG_APPLY(4),
    PAY_APPLY_FAILED(-2),
    PAY_REQUEST_FAILED(-4),
    SUBSCRIBE_BILLING_KEY_READY(40),
    SUBSCRIBE_BILLING_KEY_SUCCESS(42);
    private final int status;

    BootPayResponseStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
