package com.api.businessRegistration;

import com.response.ResponseEnum;
import com.transfer.ProtocolBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

import static com.response.ResponseEnum.*;

@Component
public class BusinessRegistrationAPI {

    @Value("${BUSINESS.REGISTRATION.KEY}")
    private String SERVICE_KEY;
    @Value("${BUSINESS.REGISTRATION.URL}")
    private String BASE_URL;

    private static final String OK_CODE = "01";


    public ResponseEnum isValid(String registration_no) {
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
            if (response.getData().get(0).getB_stt_cd().equals(OK_CODE)) return SUCCESS;
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR;
        }
        return FAIL;
    }
}
