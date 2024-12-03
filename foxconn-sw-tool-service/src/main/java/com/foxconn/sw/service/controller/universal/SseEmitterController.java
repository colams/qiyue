package com.foxconn.sw.service.controller.universal;

import com.foxconn.sw.data.dto.Request;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("api/sse")
public class SseEmitterController {

    private static Map<String, SseEmitter> sseEmitterMap = new ConcurrentHashMap<>();

    @CrossOrigin
    @GetMapping("/createConnect")
    public SseEmitter createConnect(@Valid @RequestBody Request request) {
        SseEmitter sseEmitter = new SseEmitter(0L);

        sseEmitter.onCompletion(() -> {

        });

        sseEmitter.onTimeout(() -> {

        });

        sseEmitterMap.put("", sseEmitter);

        return sseEmitter;
    }

    @GetMapping(path = "/stream-sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter handleSse() {
        SseEmitter emitter = new SseEmitter();

        // 在新线程中发送消息，以避免阻塞主线程
        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    emitter.send("data:" + "message " + i + "\n\n"); // 发送消息
                    Thread.sleep(1000); // 每秒发送一次
                }
                emitter.complete(); // 完成发送
            } catch (Exception e) {
                emitter.completeWithError(e); // 发送异常
            }
        }).start();

        return emitter;
    }

    @CrossOrigin
    @PostMapping("/broadcast")
    public void sendMessageToAllClient(@RequestBody(required = false) String msg) {
    }

//    @CrossOrigin
//    @PostMapping("/sendMessage")
//    public void sendMessageToOneClient() {
//    }

    @CrossOrigin
    @GetMapping("/closeConnect")
    public void closeConnect(@RequestParam(required = true) String clientId) {
    }

}
