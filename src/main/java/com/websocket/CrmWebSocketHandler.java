package com.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;

@Component
@Slf4j
public class CrmWebSocketHandler extends TextWebSocketHandler {
    private static final ArrayList<WebSocketSession> sessions = new ArrayList<>();

    /**
     * Plug 별 기대효과
     * 1. Stomp를 사용하지 않을 것이기에 Session별로 유저 정보를 담고 있어야함 (권한)
     * 2. 플러그 별 WebSocket Handler를 나눠야함
     *      1) 권한 있는 유저에게, 해당 플러그를 접속 중인 유저에게만 플러그 내의 변동 사항을 전달해야하므로
     *      2) 플러그 별로 이벤트가 모두 상이할 것이므로
     * **/

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage message) throws IOException {
        for (WebSocketSession sess : sessions) {
            sess.sendMessage(message);
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
