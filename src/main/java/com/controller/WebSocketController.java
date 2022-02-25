package com.controller;

import com.model.WSMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/test")
    @SendTo("/topic/message")
    public WSMessage handle(WSMessage message) {
        System.out.println("id = " + message.getId());
        System.out.println("message = " + message.getMessage());
        return message;
    }
}
