package com.foxconn.sw.service.controller.universal;

import com.foxconn.sw.data.dto.Request;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.dto.entity.universal.StringParams;
import com.foxconn.sw.data.dto.request.sse.SseMsgParams;
import com.foxconn.sw.data.dto.response.sse.EmitterUserVo;
import com.foxconn.sw.service.processor.SseEmitterProcessor;
import com.foxconn.sw.service.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/sse")
public class SseEmitterController {

    @Autowired
    SseEmitterProcessor sseEmitterProcessor;

    @GetMapping("/connect/{token}")
    public SseEmitter connect(@PathVariable String token) {
        SseEmitter sseEmitter = sseEmitterProcessor.createConnect(token);
        return sseEmitter;
    }

    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/sendMsg")
    public Response<String> sendMsg(@Valid @RequestBody Request<SseMsgParams> request) throws IOException {
        String result = sseEmitterProcessor.sendMessage(request.getData());
        return ResponseUtils.success(result, request.getTraceId());
    }

    @ApiResponse(responseCode = "0", description = "成功码")
    @PostMapping("/allUser")
    public Response<List<EmitterUserVo>> allUser(@Valid @RequestBody Request request) {
        List<EmitterUserVo> result = sseEmitterProcessor.allUser();
        return ResponseUtils.success(result, request.getTraceId());
    }


    @ApiResponse(responseCode = "0", description = "成功码")
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
