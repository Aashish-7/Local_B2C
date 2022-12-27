package com.b2c.Local.B2C.notification.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

@Service @Log4j2
public class SSEmitterService  {

    final List<SseEmitter> userSseEmittersList = new CopyOnWriteArrayList<>();

    final List<SseEmitter> localStoreOwnerSseEmittersList = new CopyOnWriteArrayList<>();

    @Async
    public CompletableFuture<SseEmitter> getUserNotification() {
        SseEmitter sseEmitter = new SseEmitter();
        sseEmitter.onCompletion(() -> userSseEmittersList.remove(sseEmitter));
        return CompletableFuture.completedFuture(sseEmitter);
    }

    @Async
    public CompletableFuture<SseEmitter> getLocalStoreOwnerNotification() {
        SseEmitter sseEmitter = new SseEmitter();
        sseEmitter.onCompletion(() -> localStoreOwnerSseEmittersList.remove(sseEmitter));
        return CompletableFuture.completedFuture(sseEmitter);
    }

    public void sendUserNotification(Object data){
        if (!userSseEmittersList.isEmpty()){
            userSseEmittersList.forEach(sseEmitter -> {
                try {
                    sseEmitter.send(SseEmitter.event().name("UserNotification").data(data));
                    log.info("Sending Message with Event [UserNotification]");
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            });
        }else {
            log.warn("SseEmitter is Empty List");
        }
    }

    public void sendLocalStoreOwnerNotification(Object data){
        if (!localStoreOwnerSseEmittersList.isEmpty()){
            localStoreOwnerSseEmittersList.forEach(sseEmitter -> {
                try {
                    sseEmitter.send(SseEmitter.event().name("LocalStoreOwnerNotification").data(data));
                    log.info("Sending Message with Event [LocalStoreOwnerNotification]");
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            });
        }else {
            log.warn("SseEmitter is Empty List");
        }
    }

}
