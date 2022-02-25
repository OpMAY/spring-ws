package com.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class WebSocketHandler extends TextWebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) {
        System.out.println("afterConnectionEstablished");
        Map<String, Object> webSocketSessionAttributes = webSocketSession.getAttributes();
        HttpSession http_session = (HttpSession) webSocketSessionAttributes.get("session");
//        String jwt_str = (String) http_session.getAttribute("token");
        System.out.println("http_session.getId() = " + http_session.getId());
    }

    @Override
    public void handleTextMessage(WebSocketSession webSocketSession, TextMessage message) throws Exception {
        System.out.println("handleTextMessage");
        Map<String, Object> webSocketSessionAttributes = webSocketSession.getAttributes();
        HttpSession http_session = (HttpSession) webSocketSessionAttributes.get("session");
//        String jwt_str = (String) http_session.getAttribute("token");
        System.out.println("http_session.getId() = " + http_session.getId());

        String payload = message.getPayload();
        System.out.println("payload = " + payload);
//        Map<String, Object> message_data = messageBinding(message);

    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        System.out.println("afterConnectionClosed");
    }

    private Map<String, Object> messageBinding(TextMessage message) {
        try {
            String payload = message.getPayload();
            TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {};
            Map<String, Object> mapping = null;
            mapping = new ObjectMapper().readValue(payload, typeRef);
            return mapping;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

}
