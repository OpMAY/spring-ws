package com.api.businessRegistration;

import com.transfer.ProtocolBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

@Component
public class BusinessRegistrationAPI {

    @Value("${BUSINESS_REGISTRATION_KEY}")
    private String SERVICE_KEY;
    private static final String BASE_URL = "https://api.odcloud.kr/api/nts-businessman/v1/status";
    private static final String OK_CODE = "01";


    public boolean isValid(String registration_no) {
        try {
            HashMap<String, String> properties = new HashMap<>();
            properties.put("Content-Type", "application/json");

            ProtocolBuilder protocolBuilder = new ProtocolBuilder()
                    .url(BASE_URL + "?serviceKey=" + SERVICE_KEY)
                    .conn()
                    .setRequestMethod("POST")
                    .setRequestProperty(properties)
                    .setDoOutput(true);

            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(registration_no);
            jsonObject.put("b_no", jsonArray);
            protocolBuilder.openWriter(jsonObject);

            Response response = (Response) protocolBuilder.openReader("UTF-8", Response.class, true);
            return response.getData().get(0).getB_stt_cd().equals(OK_CODE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
