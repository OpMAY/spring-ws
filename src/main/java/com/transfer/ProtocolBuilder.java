package com.transfer;

import com.google.gson.Gson;
import com.util.ProtocolBuilderHelper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;

@Getter
@Slf4j
/**
 * ProtocolBuilder
 * Version information
 * 2022.02.21 2 author : @woosik
 * Class Overview
 * All Rest Api Protocol Creator
 * Case : It follows the factory pattern. new ProtocolBuilder().url().conn().()...
 */
public class ProtocolBuilder {
    private URL url;
    private HttpURLConnection conn;
    private StringBuilder stringBuilder;

    public ProtocolBuilder() {
    }

    public ProtocolBuilder url(String host) throws MalformedURLException {
        url = new URL(host);
        return this;
    }

    public ProtocolBuilder url(String host, String path) throws MalformedURLException {
        url = new URL(host + path);
        return this;
    }

    public ProtocolBuilder conn() throws IOException {
        conn = (HttpURLConnection) this.url.openConnection();
        return this;
    }

    public ProtocolBuilder setRequestMethod(String requestMethod) throws ProtocolException {
        conn.setRequestMethod(requestMethod);
        return this;
    }

    public ProtocolBuilder setDoOutput(boolean output) {
        conn.setDoOutput(output);
        return this;
    }

    public ProtocolBuilder setDoInput(boolean input) {
        conn.setDoInput(input);
        return this;
    }

    public ProtocolBuilder setRequestProperty(HashMap<String, String> requestProperty) {
        requestProperty.forEach((key, value) -> {
            conn.setRequestProperty(key, value);
        });
        return this;
    }

    public ProtocolBuilder openWriter(JSONObject jsonObject) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
        bw.write(jsonObject.toString());
        bw.flush();
        bw.close();
        return this;
    }

    public ProtocolBuilder openWriter(String general_text) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
        bw.write(general_text);
        bw.flush();
        bw.close();
        return this;
    }

    public ProtocolBuilder openWriter(HashMap<String, Object> params) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
        stringBuilder = ProtocolBuilderHelper.setPostMethodParams(params);
        bw.write(stringBuilder.toString());
        bw.flush();
        bw.close();
        return this;
    }

    /**
     * Version information
     * 2022.02.21 1 author : @woosik
     * Method Overview
     * The part that imports data according to the set class
     * Case : protocolBuilder.openReader("UTF-8", Model.class, false);
     *
     * @param class_type    : Class(Model)
     * @param logging       : log is on/off
     * @param character_set : character set
     */
    public <T> Object openReader(String character_set, Class<?> class_type, boolean logging) throws IOException {

        int responseCode = conn.getResponseCode();

        BufferedReader br = null;
        if (responseCode == HttpURLConnection.HTTP_OK) {
            br = new BufferedReader(new InputStreamReader(conn.getInputStream(), character_set));
        } else {
            br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        String line = "";
        String result = "";

        while ((line = br.readLine()) != null)
            result += line;

        if (responseCode == 200) {
            if (logging) {
                log.info(result);
            }
            if (class_type == String.class) {
                return result;
            } else {
                return (T) new Gson().fromJson(result, class_type);
            }
        } else {
            if (logging) {
                log.info("filed request : " + result);
            }
            return null;
        }
    }

    /**
     * Version information
     * 2022.02.21 1 author : @woosik
     * Method Overview
     * The part that imports data according to String type
     * Case : protocolBuilder.openReader("UTF-8", false);
     *
     * @param logging       : log is on/off
     * @param character_set : character set
     */
    public String openReader(String character_set, boolean logging) throws IOException {
        int responseCode = conn.getResponseCode();

        BufferedReader br = null;
        if (responseCode == 200) {
            br = new BufferedReader(new InputStreamReader(conn.getInputStream(), character_set));
        } else {
            br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        String line = "";
        String result = "";

        while ((line = br.readLine()) != null)
            result += line;

        if (responseCode == HttpURLConnection.HTTP_OK) {
            if (logging) {
                log.info(result);
            }
            return result;
        } else {
            if (logging) {
                log.info("filed request : " + result);
            }
            return null;
        }
    }
}
