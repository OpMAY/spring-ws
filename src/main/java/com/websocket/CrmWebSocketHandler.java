package com.websocket;

import com.model.ws.crm.CrmMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;

@Component
@Slf4j
public class CrmWebSocketHandler extends TextWebSocketHandler {
    private static final ArrayList<WebSocketSession> sessions = new ArrayList<>();

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage message) throws IOException {
        CrmMessage crmMessage = (CrmMessage) message.getPayload();
        log.info("payload : {}", crmMessage);

        for (WebSocketSession sess : sessions) {
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
