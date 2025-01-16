package com.foxconn.sw.service.controller.universal;

import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.universal.StringParams;
import com.foxconn.sw.data.dto.request.sse.SseMsgParams;
import com.foxconn.sw.service.processor.SseEmitterProcessor;
import com.foxconn.sw.service.utils.ResponseUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("api/sse")
public class SseEmitterController {

    @Autowired
    SseEmitterProcessor sseEmitterProcessor;

    @PostMapping("/connect")
    public SseEmitter connect(@Valid @RequestBody Request request) {
        return sseEmitterProcessor.createConnect(request.getHead().getToken());
    }

    @PostMapping("/sendMsg")
    public Response<String> sendMsg(@Valid @RequestBody Request<SseMsgParams> request) throws IOException {
        String result = sseEmitterProcessor.sendMessage(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @PostMapping("/close")
    public Response<String> close(@RequestBody Request<StringParams> request) {
        String result = sseEmitterProcessor.closeConnect(request.getData().getParams());
        return ResponseUtils.success(result, request.getTraceId());
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

    @PostMapping("/broadcast")
    public void sendMessageToAllClient(@RequestBody(required = false) String msg) {

    }

}
