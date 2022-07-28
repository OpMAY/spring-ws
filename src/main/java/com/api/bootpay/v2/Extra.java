package com.api.bootpay.v2;

import lombok.Data;

@Data
public class Extra {
    private String open_type = "iframe";
    private String redirect_url;
    private String card_quota = "0,2,3";
    private boolean escrow;
    private String locale;
    private String offer_period;
    private boolean display_cash_result = true;
    private boolean display_success_result = false;
    private boolean display_error_result = true;
}
