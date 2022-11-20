package com.websocket;

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
        String payload = message.getPayload();
        log.info("payload : {}", payload);

        for(WebSocketSession sess: sessions) {
            sess.sendMessage(message);
        }
    }

    /* Client가 접속 시 호출되는 메서드 */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        log.info("{} 클라이언트 접속", session);
    }

    /* Client가 접속 해제 시 호출되는 메서드드 */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("{} 클라이언트 접속 해제", session);
        sessions.remove(session);
    }
}
