package com.model.sns;

import com.google.gson.Gson;
import lombok.extern.log4j.Log4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

@Service
@Log4j
public class GoogleAPI {
    private String client_id;
    private String client_secret;

    @PostConstruct
    public void GoogleAPI() {
        log.info(client_id);
        log.info(client_secret);
    }

    public HashMap<String, Object> getUserInfo(HttpServletRequest req, String code) {
        try {
            String query = "code=" + code;
            String redirectURI = URLEncoder.encode(req.getRequestURL().toString(), "UTF-8");
            query += "&client_id=" + client_id;
            query += "&client_secret=" + client_secret;
            query += "&redirect_uri=" + redirectURI;
            query += "&grant_type=authorization_code";
            String tokenJson = getHttpConnection("https://accounts.google.com/o/oauth2/token", query);
            /*System.out.println(tokenJson);*/
            if (tokenJson != null) {
                Gson gson = new Gson();
                Google token = gson.fromJson(tokenJson, Google.class);
                String res = getHttpConnection("https://www.googleapis.com/oauth2/v1/userinfo?access_token=" + token.getAccess_token());
                HashMap<String, Object> userInfo = new HashMap<>();
                if (res != null) {
                    JSONObject jsonObject = new JSONObject(res);
                    String access_token = token.getAccess_token();
                    String id_token = token.getId_token();
                    String id = jsonObject.getString("id");
                    String email = jsonObject.getString("email");
                    userInfo.put("id", id);
                    userInfo.put("email", email);
                    userInfo.put("token", id_token);
                    userInfo.put("access_token", access_token);
                    return userInfo;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getHttpConnection(String uri) {
        try {
            URL url = new URL(uri);
            HttpsURLConnection conn = null;
            conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            int responseCode = conn.getResponseCode();
            BufferedReader br = null;
            /*System.out.print("responseCode=" + responseCode);*/
            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            String inputLine;
            StringBuffer res = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                res.append(inputLine);
            }
            br.close();
            /*System.out.println(res);*/
            if (responseCode == 200) { // 정상 호출
                return res.toString();
            } else {  // 에러 발생
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

    private String getHttpConnection(String uri, String param) {
        try {
            URL url = new URL(uri);
            HttpsURLConnection conn = null;
            conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            try (OutputStream stream = conn.getOutputStream()) {
                try (BufferedWriter wd = new BufferedWriter(new OutputStreamWriter(stream))) {
                    wd.write(param);
                }
            }
            int responseCode = conn.getResponseCode();
            BufferedReader br = null;
            /*System.out.print("responseCode=" + responseCode);*/
            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            String inputLine;
            StringBuffer res = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                res.append(inputLine);
            }
            br.close();
            if (responseCode == 200) { // 정상 호출
                return res.toString();
            } else {  // 에러 발생
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

    public boolean googleLogout(String access_token) {
        /*https://oauth2.googleapis.com/revoke?token={token}*/
        try {
            String apiURL = "https://accounts.google.com/o/oauth2/revoke?";
            apiURL += "token=" + access_token;
            URL url = new URL(apiURL);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            int responseCode = conn.getResponseCode();
            BufferedReader br = null;
            /*System.out.print("responseCode=" + responseCode);*/
            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            String inputLine;
            StringBuffer res = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                res.append(inputLine);
            }
            br.close();
            if (responseCode == 200) { // 정상 호출
                return true;
            } else {  // 에러 발생
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
}
