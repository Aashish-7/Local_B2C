package com.b2c.Local.B2C.websocket.message;

import com.b2c.Local.B2C.websocket.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @MessageMapping("/userMessage")
    public void sendMsgToUser(@Payload MessageDto messageDto){
        messageService.sendMsgToUser(messageDto);
    }
}
