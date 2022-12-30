package com.b2c.Local.B2C.websocket.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class MessageSenderDto {
    private String msg;

    private String Sender;

    UUID localStoreId;
}
