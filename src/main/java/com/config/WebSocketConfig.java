package com.config;

import com.websocket.CrmWebSocketHandler;
import com.websocket.WebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // ADD Custom Socket Handler
        registry.addHandler(new WebSocketHandler(), "ws")
                .addHandler(new CrmWebSocketHandler(), "ws/crm")
                .setAllowedOrigins("*");
    }
}