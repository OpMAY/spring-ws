package com.websocket;

import com.google.gson.Gson;
import com.model.ws.WebSocketSimpleObject;
import com.validator.test.Test;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;

@Component
@Slf4j
public class WebSocketHandler extends TextWebSocketHandler {
    private static final ArrayList<WebSocketSession> sessions = new ArrayList<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        Gson gson = new Gson();
        String payload = message.getPayload();
        log.info("payload : {}", payload);

        String sender_id = session.getId();

        WebSocketSimpleObject object = gson.fromJson(payload, WebSocketSimpleObject.class);

        for (WebSocketSession sess : sessions) {
            if (sess.getId().equals(sender_id)) {
                object.setMyData(true);
                TextMessage textMessage = new TextMessage(gson.toJson(object));
                sess.sendMessage(textMessage);
            } else {
                sess.sendMessage(message);
            }
        }
    }

    /* Client가 접속 시 호출되는 메서드 */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        log.info("{} Client Connection Established", session);
    }

    /* Client가 접속 해제 시 호출되는 메서드드 */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("{} Client Connection Closed", session);
        sessions.remove(session);
    }
}
