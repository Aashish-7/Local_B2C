package com.b2c.Local.B2C.springevent;


import jakarta.servlet.annotation.WebListener;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.session.SessionCreationEvent;
import org.springframework.stereotype.Component;

@Log4j2 @Component @WebListener
public class SessionCreatedEventListen implements ApplicationListener<SessionCreationEvent> {

    @Override
    public void onApplicationEvent(SessionCreationEvent event) {
        log.error(event.getSource());
    }
}
