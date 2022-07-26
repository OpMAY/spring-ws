package com.transfer;

import java.util.HashMap;

public class ProtocolBuilderHelper {
    public static StringBuilder setGetMethodParams(HashMap<String, Object> params) {
        int index = 1;
        StringBuilder stringBuilder = new StringBuilder();
        for (HashMap.Entry<String, Object> entry : params.entrySet()) {
            if (index == 1) {
                stringBuilder.append("?" + entry.getKey() + "=" + entry.getValue());
            } else {
                stringBuilder.append("&" + entry.getKey() + "=" + entry.getValue());
            }
            index++;
        }
        return stringBuilder;
    }

    public static StringBuilder setPostMethodParams(HashMap<String, Object> params) {
        int index = 1;
        StringBuilder stringBuilder = new StringBuilder();
        for (HashMap.Entry<String, Object> entry : params.entrySet()) {
            if (index == 1) {
                stringBuilder.append(entry.getKey() + "=" + entry.getValue());
            } else {
                stringBuilder.append("&" + entry.getKey() + "=" + entry.getValue());
            }
            index++;
        }
        return stringBuilder;
    }
}
