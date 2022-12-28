package com.b2c.Local.B2C.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * A DTO for the {@link com.b2c.Local.B2C.notification.model.UserNotification} entity
 */
@AllArgsConstructor
@Getter
public class UserNotificationDto implements Serializable {
    private final Object product;
    private String message;
}