package com.api.bootpay.v1;

import lombok.Data;

/**
 * Created by ehowlsla on 2019. 8. 23..
 */
@Data
public class Item {
    public String item_name = "LE CHAT 375ml / 12.7fl.oz";
    public int qty;
    public String unique = "lechat_item_001";
    public long price = 32000;
    public String cat1;
    public String cat2;
    public String cat3;
}
