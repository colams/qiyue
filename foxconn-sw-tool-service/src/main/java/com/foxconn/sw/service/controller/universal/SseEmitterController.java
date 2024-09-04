package com.foxconn.sw.service.controller.universal;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("api/sse")
public class SseEmitterController {

    @CrossOrigin
    @GetMapping("/createConnect")
    public SseEmitter createConnect(String clientId) {
        return null;
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
