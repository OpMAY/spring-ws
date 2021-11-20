package com.model.sns;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

@Service
@Log4j
@PropertySource("classpath:sns.properties")
public class NaverAPI {

    @Value("${naver.client_id}")
    private String client_id;
    @Value("${naver.client_secret}")
    private String client_secret;

    private Naver naver;

    @PostConstruct
    public void NaverAPI() {
        log.info(client_id);
        log.info(client_secret);
        this.naver = new Naver(client_id, client_secret);
    }

    public String getAccessToken(HttpServletRequest req, String authorize_code, String state) {
        try {
            String redirectURI = URLEncoder.encode(req.getRequestURL().toString(), "UTF-8");
            String apiURL = "https://nid.naver.com/oauth2.0/token?";
            apiURL += "grant_type=" + naver.getGrant_type();
            apiURL += "&client_id=" + naver.getClient_id();
            apiURL += "&client_secret=" + naver.getClientSecret();
            apiURL += "&redirect_uri=" + redirectURI;
            apiURL += "&code=" + authorize_code;
            apiURL += "&state=" + state;

            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            BufferedReader br = null;
            System.out.print("responseCode=" + responseCode);
            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer res = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                res.append(inputLine);
            }
            br.close();
            if (responseCode == 200) {
                System.out.println("Naver Access Body" + res.toString());
                //    Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
                JsonElement element = new JsonParser().parse(res.toString());

                String access_Token = element.getAsJsonObject().get("access_token").getAsString();
                String refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
                br.close();
                return access_Token;
            } else {
                return null;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
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

    public HashMap<String, Object> getUser(String access_Token) {
        String token = access_Token; // 네이버 로그인 접근 토큰;
        String header = "Bearer " + token; // Bearer 다음에 공백 추가

        String apiURL = "https://openapi.naver.com/v1/nid/me";

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", header);
        String responseBody = get(apiURL, requestHeaders);
        if (responseBody != null) {
            JsonElement element = new JsonParser().parse(responseBody);
            JsonObject properties = element.getAsJsonObject().get("response").getAsJsonObject();

            String nickname = null;
            String email = null;
            String id = null;

            if (!properties.getAsJsonObject().get("nickname").isJsonNull())
                nickname = properties.getAsJsonObject().get("nickname").getAsString();
            if (!properties.getAsJsonObject().get("email").isJsonNull())
                email = properties.getAsJsonObject().get("email").getAsString();
            if (!properties.getAsJsonObject().get("id").isJsonNull())
                id = properties.getAsJsonObject().get("id").getAsString();

            //    요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
            HashMap<String, Object> userInfo = new HashMap<>();
            userInfo.put("nickname", nickname);
            userInfo.put("email", email);
            userInfo.put("id", id);
            return userInfo;
        } else {
            return null;
        }
    }

    private static String get(String apiUrl, Map<String, String> requestHeaders) {
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            con.disconnect();
        }
    }

    private static HttpURLConnection connect(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String readBody(InputStream body) {
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean naverLogout(String access_Token) {
        String token = access_Token; // 네이버 로그인 접근 토큰;
        String header = "Bearer " + token; // Bearer 다음에 공백 추가
        String apiURL = "https://nid.naver.com/oauth2.0/token?";
        apiURL += "&grant_type=" + naver.getGrant_delete_type();
        apiURL += "&client_id=" + naver.getClient_id();
        apiURL += "&client_secret=" + naver.getClientSecret();
        apiURL += "&access_token=" + access_Token;
        apiURL += "&service_provider=" + naver.getService_provider();
        Map<String, String> requestHeaders = new HashMap<>();

        requestHeaders.put("Authorization", header);
        String responseBody = get(apiURL, requestHeaders);
        if (responseBody != null) {
            return true;
        } else {
            return false;
        }
    }
}
