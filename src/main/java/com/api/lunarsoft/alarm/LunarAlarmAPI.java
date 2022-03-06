package com.api.lunarsoft.alarm;

import com.api.lunarsoft.alarm.custom.SignUp;
import com.google.gson.Gson;
import com.transfer.ProtocolBuilder;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.HashMap;

/**
 * LunarAlarmAPI
 * Version information
 * 2022.02.21 1 author : @woosik
 * Class Overview
 * This is the Lunarsoft API (KakaoTalk Agency : Kakao Alarm Service).
 * Keyword : E-Commerce Platform, Shop ...
 * Case : lunarAlarmAPI.signUpTest(SignUp signUp) (@Autowired)
 */
@Slf4j
@Service
public class LunarAlarmAPI {
    @Value("${LUNARSOFT.ID}")
    private String CLIENT_ID;
    @Value("LUNARSOFT.API")
    private String CLIENT_SECRET;
    private final String LUNARSOFT_ALARM_URL = "https://jupiter.lunasoft.co.kr/api/AlimTalk/message/send";

    /**
     * Version information
     * 2022.02.21 1 author : @woosik
     * Method Overview
     * This is the actual function that sends an alarm.
     */
    public ResponseMessage sendAlarm(AlarmTemplate alarmTemplate) {
        alarmTemplate.setApi_key(CLIENT_SECRET);
        alarmTemplate.setUserid(CLIENT_ID);
        alarmTemplate.getMessages().get(0).setSms_content(alarmTemplate.getMessages().get(0).getMsg_content());
        try {
            HashMap<String, String> properties = new HashMap<>();
            properties.put("content-type", "application/json");

            ProtocolBuilder protocolBuilder = new ProtocolBuilder()
                    .url(LUNARSOFT_ALARM_URL)
                    .conn()
                    .setRequestMethod("POST")
                    .setDoOutput(true)
                    .setRequestProperty(properties);
            protocolBuilder.openWriter(new Gson().toJson(alarmTemplate));
            ResponseMessage responseMessage = (ResponseMessage) protocolBuilder.openReader("UTF-8", ResponseMessage.class, true);
            log.info(responseMessage.toString());
            return responseMessage;
        } catch (ProtocolException e) {
            e.printStackTrace();
            return null;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Version information
     * 2022.02.21 1 author : @woosik
     * Method Overview
     * Lunarsoft Alarm Test function (Register Message in kakaotalk)
     * @Caution
     * Be careful not to make any mistakes
     * It must be used as it is given by the company.
     */
    public boolean signUpTest(SignUp signUp) {
        AlarmTemplate alarmTemplate = new AlarmTemplate();
        alarmTemplate.setTemplate_id(signUp.getTemplate_id());
        alarmTemplate.getMessages().get(0).setTel_num(signUp.getTel_no());
        alarmTemplate.getMessages().get(0).setMsg_content("[" + signUp.getShop_name() + "]\n" +
                "안녕하세요. " + signUp.getUser_name() + "님!\n" +
                "\n" +
                "회원 가입 해주셔서 감사합니다. (축하)\n" +
                "" + signUp.getUser_name() + "님의 회원가입 정보 안내드립니다.\n" +
                "\n" +
                "" + signUp.getUser_name() + "님의\n" +
                "[" + signUp.getShop_name() + "] ID : " + signUp.getUser_id() + "\n" +
                "\n" +
                "" + signUp.getUser_name() + "님을 위한 상품들이 많이 준비되어 있습니다.\n" +
                "앞으로 많은 이용 부탁드립니다. (뽀뽀)\n" +
                "\n" +
                "\n" +
                "▷ " + signUp.getShop_name() + " 바로가기\n" +
                "" + signUp.getUrl() + "\n" +
                "고객센터\n" +
                "(" + signUp.getSend_phone() + ")");
        alarmTemplate.getMessages().get(0).setSms_content(alarmTemplate.getMessages().get(0).getMsg_content());
        ResponseMessage responseMessage = sendAlarm(alarmTemplate);
        if (responseMessage != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Version information
     * 2022.02.21 1 author : @woosik
     * Method Overview
     * Sample Data Set
     */
    public JSONObject getTestSample() {
        return new JSONObject("{\"messages\":[{\"no\":\"1\",\"use_sms\":\"1\",\"btn_url\":[{\"url_mobile\":\"http://www.sustable.kr\",\"url_pc\":\"http://www.sustable.kr\"}],\"sms_content\":\"sms test\",\"msg_content\":\"message test\",\"tel_num\":\"01045299453\"}],\"template_id\":26077}");
    }
}
