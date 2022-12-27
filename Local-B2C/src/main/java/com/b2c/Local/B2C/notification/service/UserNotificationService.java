package com.b2c.Local.B2C.notification.service;

import com.b2c.Local.B2C.notification.dao.UserNotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserNotificationService {

    UserNotificationRepository userNotificationRepository;

    @Autowired
    public UserNotificationService(UserNotificationRepository userNotificationRepository) {
        this.userNotificationRepository = userNotificationRepository;
    }
}
