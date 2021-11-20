package com.model.sns;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.log4j.Log4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;

@Service
@Log4j
@PropertySource("classpath:sns.properties")
public class KakaoAPI {

    @Value("${kakao.client_id}")
    private String client_id;

    @Value("${kakao.pay_key}")
    private String pay_key;

    private Kakao kakao;

    @PostConstruct
    public void KakaoAPI() {
        log.info(client_id);
        log.info(pay_key);
        kakao = new Kakao(client_id, pay_key);
    }

    /**
     * Kakao OAuth2.0 Token Getter
     */
    public String getAccessToken(HttpServletRequest req, String authorize_code) {
        try {
            /*TODO HTTP Protocol Builder*/
            URL url = new URL(kakao.getToken_request());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //    POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod(kakao.getType());
            conn.setDoOutput(kakao.isOutput());
            //    POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));

            /*TODO Kakao Protocol Builder*/
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=" + kakao.getGrant_type());
            sb.append("&client_id=" + kakao.getClient_id());
            /*http://localhost:8080/login.do*/
            sb.append("&redirect_uri=" + req.getRequestURL());
            sb.append("&code=" + authorize_code);

            bw.write(sb.toString());
            bw.flush();

            //    결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            BufferedReader br = null;
            /*System.out.println("KakaoAPI Response Code : " + responseCode);*/
            if (responseCode == 200) {
                //    요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            /*System.out.println("KakaoAPI Response Body : " + result);*/

            if (responseCode == 200) {
                //    Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
                JsonElement element = new JsonParser().parse(result);

                String access_Token = element.getAsJsonObject().get("access_token").getAsString();
                String refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
                /*System.out.println("KakaoAPI Access Token : " + access_Token);
                System.out.println("KakaoAPI Refresh Token : " + refresh_Token);*/
                br.close();
                bw.close();
                return access_Token;
            } else {
                br.close();
                bw.close();
                return null;
            }
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
     * Kakao User Get Information
     */
    public HashMap<String, Object> getUser(String access_Token) {
        try {
            /*TODO HTTP Protocol Builder*/
            URL url = new URL(kakao.getUser_request());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(kakao.getType());
            //요청에 필요한 Header에 포함될 내용
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded;charset=utf-8");
            int responseCode = conn.getResponseCode();
            /* System.out.println("responseCode : " + responseCode);*/
            BufferedReader br = null;
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null)
                result += line;

            if (responseCode == 200) {
                JsonElement element = new JsonParser().parse(result);

                String id = null;
                String nickname = null;
                String email = null;

                if (!element.getAsJsonObject().get("id").isJsonNull())
                    id = String.valueOf(element.getAsJsonObject().get("id"));

                JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
                JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
                JsonObject profile = kakao_account.getAsJsonObject("profile");

                if (!profile.get("nickname").isJsonNull())
                    nickname = profile.get("nickname").getAsString();
                if (!kakao_account.getAsJsonObject().get("email").isJsonNull())
                    email = kakao_account.getAsJsonObject().get("email").getAsString();

                //    요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
                HashMap<String, Object> userInfo = new HashMap<>();
                userInfo.put("id", id);
                userInfo.put("nickname", nickname);
                userInfo.put("email", email);
                return userInfo;
            } else {
                return null;
            }
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
     * Kakao Logout
     */
    public boolean kakaoLogout(String access_Token) {
        try {
            URL url = new URL(kakao.getLogout_request());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);
            int responseCode = conn.getResponseCode();
            /*System.out.println("responseCode : " + responseCode);*/
            BufferedReader br = null;
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            String result = "";
            String line = "";
            while ((line = br.readLine()) != null) {
                result += line;
            }
            if (responseCode == 200) {
                return true;
            } else {
                return false;
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
            return false;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Request
     * curl -v -X POST "https://kapi.kakao.com/v1/payment/ready" \
     * -H "Authorization: KakaoAK {APP_ADMIN_KEY}" \
     * --data-urlencode "cid=TC0ONETIME" \
     * --data-urlencode "partner_order_id=partner_order_id" \
     * --data-urlencode "partner_user_id=partner_user_id" \
     * --data-urlencode "item_name=초코파이" \
     * --data-urlencode "quantity=1" \
     * --data-urlencode "total_amount=2200" \
     * --data-urlencode "vat_amount=200" \
     * --data-urlencode "tax_free_amount=0" \
     * --data-urlencode "approval_url=https://developers.kakao.com/success" \
     * --data-urlencode "fail_url=https://developers.kakao.com/fail" \
     * --data-urlencode "cancel_url=https://developers.kakao.com/cancel"
     * <p>
     * Name	Type	Description	Required
     * cid	String	가맹점 코드, 10자	O
     * cid_secret	String	가맹점 코드 인증키, 24자, 숫자와 영문 소문자 조합	X
     * partner_order_id	String	가맹점 주문번호, 최대 100자	O
     * partner_user_id	String	가맹점 회원 id, 최대 100자	O
     * item_name	String	상품명, 최대 100자	O
     * item_code	String	상품코드, 최대 100자	X
     * quantity	Integer	상품 수량	O
     * total_amount	Integer	상품 총액	O
     * tax_free_amount	Integer	상품 비과세 금액	O
     * vat_amount	Integer	상품 부가세 금액
     * 값을 보내지 않을 경우 다음과 같이 VAT 자동 계산
     * (상품총액 - 상품 비과세 금액)/11 : 소숫점 이하 반올림	X
     * approval_url	String	결제 성공 시 redirect url, 최대 255자	O
     * cancel_url	String	결제 취소 시 redirect url, 최대 255자	O
     * fail_url	String	결제 실패 시 redirect url, 최대 255자	O
     * available_cards	JSON Array	결제 수단으로써 사용 허가할 카드사 목록, 지정하지 않으면 모든 카드사 허용
     * 현재 SHINHAN, KB, HYUNDAI, LOTTE, SAMSUNG, NH, BC, HANA, CITI, KAKAOBANK, KAKAOPAY, WOORI, GWANGJU, SUHYUP, SHINHYUP, JEONBUK, JEJU, SC 지원
     * ex) [“HANA”, “BC”]	X
     * payment_method_type	String	사용 허가할 결제 수단, 지정하지 않으면 모든 결제 수단 허용
     * CARD 또는 MONEY 중 하나	X
     * install_month	Integer	카드 할부개월, 0~12	X
     * custom_json	JSON Map {String:String}	결제 화면에 보여줄 사용자 정의 문구, 카카오페이와 사전 협의 필요
     * ex) iOS에서 사용자 인증 완료 후 가맹점 앱으로 자동 전환하는 방법(iOS만 예외 처리, 안드로이드 동작 안 함)
     * - 다음과 같이 return_custom_url key 정보에 앱스킴을 넣어서 전송
     * "return_custom_url":"kakaotalk://"	X
     * <p>
     * Response
     * HTTP/1.1 200 OK
     * Content-type: application/json;charset=UTF-8
     * {
     * "tid": "T1234567890123456789",
     * "next_redirect_app_url": "https://mockup-pg-web.kakao.com/v1/xxxxxxxxxx/aInfo",
     * "next_redirect_mobile_url": "https://mockup-pg-web.kakao.com/v1/xxxxxxxxxx/mInfo",
     * "next_redirect_pc_url": "https://mockup-pg-web.kakao.com/v1/xxxxxxxxxx/info",
     * "android_app_scheme": "kakaotalk://kakaopay/pg?url=https://mockup-pg-web.kakao.com/v1/xxxxxxxxxx/order",
     * "ios_app_scheme": "kakaotalk://kakaopay/pg?url=https://mockup-pg-web.kakao.com/v1/xxxxxxxxxx/order",
     * "created_at": "2016-11-15T21:18:22"
     * }
     */
    public KakaoPay kakaoPayReady() {
        String request = "";
        HashMap<String, String> params = new HashMap<>();
        params.put("cid", "TC0ONETIME");
        params.put("partner_order_id", "partner_order_id");
        params.put("partner_user_id", "partner_user_id");
        params.put("item_name", "초코파이");
        params.put("quantity", "1");
        params.put("total_amount", "2200"); //VAT : (상품총액 - 상품 비과세 금액)/11 : 소숫점 이하 반올림
        params.put("vat_amount", "200"); //비과세
        params.put("tax_free_amount", "0"); //비과세
        params.put("approval_url", "http://localhost:8080/auth/kpaySuccess.do");
        params.put("cancel_url", "http://localhost:8080/auth/kpayCancel.do");
        params.put("fail_url", "http://localhost:8080/auth/kpayFail.do");

        request = kakao.getPay_host() + kakao.getPay_host_end_point_ready() + "?";

        for (String key : params.keySet()) {
            request += key + "=" + params.get(key) + "&";
        }

        request = request.substring(0, request.length() - 1);
        log.info(request);

        try {
            /*TODO HTTP Protocol Builder*/
            URL url = new URL(request);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(kakao.getType());
            conn.setRequestProperty("Authorization", "KakaoAK " + kakao.getApp_admin_key());
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded;charset=utf-8");
            int responseCode = conn.getResponseCode();
            BufferedReader br = null;
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            String line = "";
            String result = "";
            while ((line = br.readLine()) != null) {
                result += line;
            }
            if (responseCode == 200) {
                log.info(result);
                JSONObject jsonObject = new JSONObject(result);
                KakaoPay kakaoPay = new KakaoPay();
                kakaoPay.setNext_redirect_pc_url(jsonObject.getString("next_redirect_pc_url"));
                kakaoPay.setTid(jsonObject.getString("tid"));
                return kakaoPay;
            } else {
                log.info(responseCode);
                log.info(result);
                return null;
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
            return null;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * POST /v1/payment/approve HTTP/1.1
     * Host: kapi.kakao.com
     * Authorization: KakaoAK {APP_ADMIN_KEY}
     * Content-type: application/x-www-form-urlencoded;charset=utf-8
     * <p>
     * curl -v -X POST "https://kapi.kakao.com/v1/payment/approve" \
     * -H "Authorization: KakaoAK {APP_ADMIN_KEY}' \
     * --data-urlencode "cid=TC0ONETIME" \
     * --data-urlencode "tid=T1234567890123456789" \
     * --data-urlencode "partner_order_id=partner_order_id" \
     * --data-urlencode "partner_user_id=partner_user_id" \
     * --data-urlencode "pg_token=xxxxxxxxxxxxxxxxxxxx"
     * <p>
     * Request
     * Name	Type	Description	Required
     * cid	String	가맹점 코드, 10자	O
     * cid_secret	String	가맹점 코드 인증키, 24자, 숫자+영문 소문자 조합	X
     * tid	String	결제 고유번호, 결제 준비 API 응답에 포함	O
     * partner_order_id	String	가맹점 주문번호, 결제 준비 API 요청과 일치해야 함	O
     * partner_user_id	String	가맹점 회원 id, 결제 준비 API 요청과 일치해야 함	O
     * pg_token	String	결제승인 요청을 인증하는 토큰
     * 사용자 결제 수단 선택 완료 시, approval_url로 redirection해줄 때 pg_token을 query string으로 전달	O
     * payload	String	결제 승인 요청에 대해 저장하고 싶은 값, 최대 200자	X
     * total_amount	Integer	상품 총액, 결제 준비 API 요청과 일치해야 함	X
     * <p>
     * Response: 결제 수단 MONEY일 때 성공
     * HTTP/1.1 200 OK
     * Content-type: application/json;charset=UTF-8
     * {
     * "aid": "A5678901234567890123",
     * "tid": "T1234567890123456789",
     * "cid": "TC0ONETIME",
     * "partner_order_id": "partner_order_id",
     * "partner_user_id": "partner_user_id",
     * "payment_method_type": "MONEY",
     * "item_name": "초코파이",
     * "quantity": 1,
     * "amount": {
     * "total": 2200,
     * "tax_free": 0,
     * "vat": 200,
     * "point": 0,
     * "discount": 0
     * },
     * "created_at": "2016-11-15T21:18:22",
     * "approved_at": "2016-11-15T21:20:47"
     * }
     * <p>
     * Response: 결제 수단 CARD일 때 성공
     * HTTP/1.1 200 OK
     * Content-type: application/json;charset=UTF-8
     * {
     * "cid": "TC0ONETIME",
     * "aid": "A5678901234567890123",
     * "tid": "T1234567890123456789",
     * "partner_user_id": "partner_user_id",
     * "partner_order_id": "partner_order_id",
     * "payment_method_type": "CARD",
     * "item_name": "카페아메리카노",
     * "quantity": 1,
     * "amount": {
     * "total": 3200,
     * "tax_free": 0,
     * "vat": 0,
     * "discount": 0,
     * "point": 0
     * },
     * "card_info": {
     * "interest_free_install": "N",
     * "bin": "621640",
     * "card_type": "체크",
     * "card_mid": "123456789",
     * "approved_id": "12345678",
     * "install_month": "00",
     * "purchase_corp": "비씨카드",
     * "purchase_corp_code": "01",
     * "issuer_corp": "수협카드",
     * "issuer_corp_code": "13",
     * "kakaopay_purchase_corp": "비씨카드",
     * "kakaopay_purchase_corp_code": "104",
     * "kakaopay_issuer_corp": "수협은행",
     * "kakaopay_issuer_corp_code": "212"
     * },
     * "created_at": "2019-05-21T11:18:24",
     * "approved_at": "2019-05-21T11:18:32"
     * }
     * <p>
     * Sample: 결제 수단 CARD 일 때 실패
     * HTTP/1.1 400 Bad Request
     * Content-type: application/json;charset=UTF-8
     * {
     * "code": -780,
     * "msg": "approval failure!",
     * "extras": {
     * "method_result_code": "USER_LOCKED",
     * "method_result_message": "진행중인 거래가 있습니다. 잠시 후 다시 시도해 주세요."
     * }
     * }
     */
    public KakaoPay kakaoPayApprove(KakaoPay kakaoPay) {
        String request = "";
        HashMap<String, String> params = new HashMap<>();
        params.put("cid", "TC0ONETIME");
        params.put("tid", kakaoPay.getTid());
        params.put("partner_order_id", "partner_order_id");
        params.put("partner_user_id", "partner_user_id");
        params.put("pg_token", kakaoPay.getBg_token());

        request = kakao.getPay_host() + kakao.getPay_host_end_point_approve() + "?";

        for (String key : params.keySet()) {
            request += key + "=" + params.get(key) + "&";
        }

        request = request.substring(0, request.length() - 1);
        log.info(request);

        try {
            /*TODO HTTP Protocol Builder*/
            URL url = new URL(request);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(kakao.getType());
            conn.setRequestProperty("Authorization", "KakaoAK " + kakao.getApp_admin_key());
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded;charset=utf-8");
            int responseCode = conn.getResponseCode();
            BufferedReader br = null;
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            String line = "";
            String result = "";
            while ((line = br.readLine()) != null) {
                result += line;
            }
            if (responseCode == 200) {
                log.info(result);
                JSONObject jsonObject = new JSONObject(result);
                log.info(jsonObject);
                kakaoPay.setTid(jsonObject.getString("tid"));
                kakaoPay.setAid(jsonObject.getString("cid"));
                kakaoPay.setCid(jsonObject.getString("cid"));
                kakaoPay.setPayment_method_type(jsonObject.getString("payment_method_type"));
                kakaoPay.setItem_name(jsonObject.getString("item_name"));
                kakaoPay.setQuantity(jsonObject.getInt("quantity"));
                kakaoPay.setAmount(jsonObject.getJSONObject("amount"));
                kakaoPay.setCreated_at(jsonObject.getString("created_at"));
                kakaoPay.setApproved_at(jsonObject.getString("approved_at"));
                return kakaoPay;
            } else {
                log.info(responseCode);
                log.info(result);
                return null;
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
            return null;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * POST /v1/payment/cancel HTTP/1.1
     * Host: kapi.kakao.com
     * Authorization: KakaoAK {APP_ADMIN_KEY}
     * Content-type: application/x-www-form-urlencoded;charset=utf-8
     * Parameter
     * Name	Type	Description	Required
     * cid	String	가맹점 코드, 10자	O
     * cid_secret	String	가맹점 코드 인증키, 24자, 숫자+영문 소문자 조합	X
     * tid	String	결제 고유번호	O
     * cancel_amount	Integer	취소 금액	O
     * cancel_tax_free_amount	Integer	취소 비과세 금액	O
     * cancel_vat_amount	Integer	취소 부가세 금액
     * 요청 시 값을 전달하지 않을 경우, (취소 금액 - 취소 비과세 금액)/11, 소숫점이하 반올림	X
     * cancel_available_amount	Integer	취소 가능 금액(결제 취소 요청 금액 포함)	X
     * payload	String	해당 요청에 대해 저장하고 싶은 값, 최대 200자	X
     * <p>
     * curl -v -X POST "https://kapi.kakao.com/v1/payment/cancel" \
     * -H "Authorization: KakaoAK {APP_ADMIN_KEY}" \
     * -d "cid=TC0ONETIME" \
     * -d "tid=T1234567890123456789" \
     * -d "cancel_amount=2200" \
     * -d "cancel_tax_free_amount=0" \
     * -d "cancel_vat_amount=200" \
     * -d "cancel_available_amount=4000"
     * <p>
     * Response: 결제 취소 성공
     * HTTP/1.1 200 OK
     * Content-type: application/json;charset=UTF-8
     * {
     * "aid": "A5678901234567890123",
     * "tid": "T1234567890123456789",
     * "cid": "TC0ONETIME",
     * "status": "CANCEL_PAYMENT",
     * "partner_order_id": "partner_order_id",
     * "partner_user_id": "partner_user_id",
     * "payment_method_type": "MONEY",
     * "item_name": "초코파이",
     * "quantity": 1,
     * "amount": {
     * "total": 2200,
     * "tax_free": 0,
     * "vat": 200,
     * "point": 0,
     * "discount": 0
     * },
     * "canceled_amount": {
     * "total": 2200,
     * "tax_free": 0,
     * "vat": 200,
     * "point": 0,
     * "discount": 0
     * },
     * "cancel_available_amount": {
     * "total": 0,
     * "tax_free": 0,
     * "vat": 0,
     * "point": 0,
     * "discount": 0
     * },
     * "created_at": "2016-11-15T21:18:22",
     * "approved_at": "2016-11-15T21:20:48",
     * "canceled_at": "2016-11-15T21:28:28"
     * }
     * <p>
     * Response: 결제 취소 실패
     * HTTP/1.1 400 Bad Request
     * Content-type: application/json;charset=UTF-8
     * {
     * "code": -781,
     * "msg": "cancel failure!",
     * "extras": {
     * "method_result_code": "6666",
     * "method_result_message": "원거래없음"
     * }
     * }
     */
    public KakaoPay kakaoPayCancel(KakaoPay kakaoPay) {
        String request = "";
        HashMap<String, String> params = new HashMap<>();
        try {
            params.put("cid", kakaoPay.getCid());
            params.put("tid", kakaoPay.getTid());
            params.put("cancel_amount", String.valueOf(kakaoPay.getAmount().getInt("total")));
            params.put("cancel_tax_free_amount", String.valueOf(kakaoPay.getAmount().getInt("tax_free")));
            params.put("cancel_vat_amount", String.valueOf(kakaoPay.getAmount().getInt("vat"))); //없으면 자동 계산
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        request = kakao.getPay_host() + kakao.getPay_host_end_point_cancel() + "?";

        for (String key : params.keySet()) {
            request += key + "=" + params.get(key) + "&";
        }

        request = request.substring(0, request.length() - 1);
        log.info(request);

        try {
            /*TODO HTTP Protocol Builder*/
            URL url = new URL(request);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(kakao.getType());
            conn.setRequestProperty("Authorization", "KakaoAK " + kakao.getApp_admin_key());
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded;charset=utf-8");
            int responseCode = conn.getResponseCode();
            BufferedReader br = null;
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            String line = "";
            String result = "";
            while ((line = br.readLine()) != null) {
                result += line;
            }
            if (responseCode == 200) {
                log.info(result);
                JSONObject jsonObject = new JSONObject(result);
                return kakaoPay;
            } else {
                log.info(responseCode);
                log.info(result);
                return null;
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
            return null;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
