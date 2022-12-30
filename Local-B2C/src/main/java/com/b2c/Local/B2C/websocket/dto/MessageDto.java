package com.b2c.Local.B2C.websocket.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDto {

    String msg;

    String sender;

    String localStoreId;
}
