package com.b2c.Local.B2C.common.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@RestController
public class SSEmitterController {


    @GetMapping("getNotification")
    public void getNotification() throws IOException {
        SseEmitter sseEmitter = new SseEmitter();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                for (int i = 0; i < 20; i++) {
                    sseEmitter.send(SseEmitter.event().name("wishlist").data("hello").build());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                sseEmitter.complete();
            }
        });
        executor.shutdown();
    }
}
