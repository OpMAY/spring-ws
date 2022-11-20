package com.model.ws.crm;

import com.model.common.Time;
import lombok.Data;
import org.springframework.web.socket.WebSocketMessage;

@Data
public class CrmMessage extends Time implements WebSocketMessage<CrmMessage>  {
    private String message;
    private CrmMessageType type;
    private CrmPayload payload;

    @Override
    public CrmMessage getPayload() {
        return this;
    }

    @Override
    public int getPayloadLength() {
        return this.message.length();
    }

    @Override
    public boolean isLast() {
        return false;
    }
}
