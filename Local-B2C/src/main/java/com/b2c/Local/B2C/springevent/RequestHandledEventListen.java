package com.b2c.Local.B2C.springevent;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.RequestHandledEvent;

@Log4j2 @Component
public class RequestHandledEventListen implements ApplicationListener<RequestHandledEvent> {

    @Override
    public void onApplicationEvent(RequestHandledEvent event) {
        log.info("Incoming Request For "+event.getDescription());
    }
}
