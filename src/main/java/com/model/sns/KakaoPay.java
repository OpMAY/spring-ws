package com.model.sns;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

@Data
@Setter
@Getter
public class KakaoPay {
    private String next_redirect_pc_url;
    private String tid;
    private String aid;
    private String cid;
    private String bg_token;
    private JSONObject amount;
    private String item_name;
    private int quantity;
    private String payment_method_type;
    private String created_at;
    private String approved_at;
}
