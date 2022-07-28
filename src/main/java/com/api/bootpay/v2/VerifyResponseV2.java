package com.api.bootpay.v2;

import com.model.bootpay.v1.ResDefault;
import lombok.Data;

@Data
public class VerifyResponseV2 extends ResDefault {
    private BootPayV2 data;
}
