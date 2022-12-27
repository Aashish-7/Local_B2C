package com.b2c.Local.B2C.notification.controller;
import com.b2c.Local.B2C.notification.service.SSEmitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping("/notification")
public class SSEmitterController {

    SSEmitterService ssEmitterService;

    @Autowired
    public SSEmitterController(SSEmitterService ssEmitterService) {
        this.ssEmitterService = ssEmitterService;
    }

    @GetMapping("/getUserNotification")
    public CompletableFuture<SseEmitter> getUserNotification(){
       return ssEmitterService.getUserNotification();
    }

    @GetMapping("/getLocalStoreOwnerNotification")
    public CompletableFuture<SseEmitter> getLocalStoreOwnerNotification(){
        return ssEmitterService.getLocalStoreOwnerNotification();
    }

}
