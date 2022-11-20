package com.model.ws;

import lombok.Data;

@Data
public class WebSocketSimpleObject {
    private String type;
    private Object data;
    private boolean isMyData;
}
