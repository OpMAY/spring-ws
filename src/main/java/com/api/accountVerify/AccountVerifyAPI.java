package com.api.accountVerify;

import com.transfer.ProtocolBuilder;
import com.util.TokenGenerator;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Component
public class AccountVerifyAPI {

    @Value("${ACCOUNT_VERIFY_CLIENT_ID}")
    private String CLIENT_ID;
    @Value("${ACCOUNT_VERIFY_CLIENT_SECRET}")
    private String CLIENT_SECRET;
    private static final String TOKEN_URL = "https://testapi.openbanking.or.kr/oauth/2.0/token";
    private static final String BASE_URL = "https://testapi.openbanking.or.kr/v2.0/inquiry/real_name";

    public TokenResponse getAccessToken() {
        try {
            HashMap<String, String> header = new HashMap<>();
            header.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

            ProtocolBuilder protocolBuilder = new ProtocolBuilder()
                    .url(TOKEN_URL)
                    .conn()
                    .setRequestMethod("POST")
                    .setRequestProperty(header)
                    .setDoOutput(true);

            HashMap<String, Object> body = new HashMap<>();
            body.put("client_id", CLIENT_ID);
            body.put("client_secret", CLIENT_SECRET);
            body.put("scope", "oob");
            body.put("grant_type", "client_credentials");

            protocolBuilder.openWriter(body);
            return (TokenResponse) protocolBuilder.openReader("UTF-8", TokenResponse.class, true);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public boolean isValid(TokenResponse token) {
        try {
            HashMap<String, String> header = new HashMap<>();
            header.put("Content-Type", "application/json");
            header.put("Authorization", "Bearer " + token.getAccess_token());

            ProtocolBuilder protocolBuilder = new ProtocolBuilder()
                    .url(BASE_URL)
                    .conn()
                    .setRequestMethod("POST")
                    .setRequestProperty(header)
                    .setDoOutput(true);

            DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("bank_tran_id", token.getClient_use_code() + "U" + TokenGenerator.RandomTokenIntAndCapital(9)); // 은행거래고유번호
            jsonObject.put("bank_code_std", getBankCode(token.getBank_type())); // 개설기관.표준코드
            jsonObject.put("account_num", token.getAccount_num()); // 계좌번호
            jsonObject.put("account_holder_info_type", " "); //예금주 실명번호 구분코드
            jsonObject.put("account_holder_info", token.getBirth_date()); //예금주 실명번호 구분코드 : 생년월일
            jsonObject.put("tran_dtime", dateFormat.format(new Date())); // 요청일시

            protocolBuilder.openWriter(jsonObject);
            BankInfoResponse response = (BankInfoResponse) protocolBuilder.openReader("UTF-8", BankInfoResponse.class, true);
            return response.getRsp_code().equals("A0000");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getBankCode(String bankName) {
        if (bankName == null) return "";
        switch (bankName) {
            case "산업":
                return "002";
            case "기업":
                return "003";
            case "국민":
                return "004";
            case "수협":
                return "007";
            case "농협":
                return "011";
            case "농협중앙":
                return "012";
            case "우리":
                return "020";
            case "SC제일":
                return "023";
            case "씨티":
                return "027";
            case "대구":
                return "031";
            case "부산":
                return "032";
            case "광주":
                return "034";
            case "제주":
                return "035";
            case "전북":
                return "037";
            case "경남":
                return "039";
            case "새마을":
                return "045";
            case "신협":
                return "048";
            case "상호저축":
                return "050";
            case "산림조합":
                return "064";
            case "우체국":
                return "071";
            case "KEB하나":
                return "081";
            case "신한":
                return "088";
            case "케이뱅크":
                return "089";
            case "카카오":
                return "090";
            case "오픈":
                return "097";
            default:
                return "is not Exist code";
        }
    }

}
