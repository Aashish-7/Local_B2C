package com.b2c.Local.B2C.springevent;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Log4j2 @Component
public class ApplicationEventsListen {

    @EventListener({AuthenticationSuccessEvent.class, InteractiveAuthenticationSuccessEvent.class})
    public void onAuthenticationSuccessEvent(AuthenticationSuccessEvent authenticationSuccessEvent){
        log.info(authenticationSuccessEvent.getTimestamp());
    }
}
