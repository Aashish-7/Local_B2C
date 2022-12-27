package com.b2c.Local.B2C.notification.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class SSEmitterService  {

    final List<SseEmitter> sseEmitterList = new CopyOnWriteArrayList<>();

    @Async
    public CompletableFuture<SseEmitter> getNotification() {
        SseEmitter sseEmitter = new SseEmitter();
        sseEmitter.onCompletion(() -> sseEmitterList.remove(sseEmitter));
        return CompletableFuture.completedFuture(sseEmitter);
    }

}
