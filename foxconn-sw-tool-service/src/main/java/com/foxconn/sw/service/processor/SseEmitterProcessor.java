package com.foxconn.sw.service.processor;

import com.foxconn.sw.common.utils.UUIDUtils;
import com.foxconn.sw.data.dto.request.sse.SseMsgParams;
import com.foxconn.sw.data.dto.response.sse.EmitterUserVo;
import com.foxconn.sw.service.SseEmitterUTF8;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Component
public class SseEmitterProcessor {

    private static final Logger log = LoggerFactory.getLogger(SseEmitterProcessor.class);

    /**
     * 容器，保存连接，用于输出返回 ;可使用其他方法实现
     */
    private static final Map<String, SseEmitterUTF8> sseCache = new ConcurrentHashMap<>();


    /**
     * 根据客户端id获取SseEmitter对象
     *
     * @param clientId 客户端ID
     */
    public SseEmitterUTF8 getSseEmitterByClientId(String clientId) {
        return sseCache.get(clientId);
    }


    /**
     * 创建连接
     *
     * @param clientId 客户端ID
     */
    public SseEmitterUTF8 createConnect(String clientId) {
        if (sseCache.containsKey(clientId)) {
            return sseCache.get(clientId);
        }

        // 设置超时时间，0表示不过期。默认30秒，超过时间未完成会抛出异常：AsyncRequestTimeoutException
        SseEmitterUTF8 sseEmitter = new SseEmitterUTF8(0L);
        // 是否需要给客户端推送ID
        if (StringUtils.isBlank(clientId)) {
            clientId = UUIDUtils.getUuid();
        }
        // 注册回调
        sseEmitter.onCompletion(completionCallBack(clientId));     // 长链接完成后回调接口(即关闭连接时调用)
        sseEmitter.onTimeout(timeoutCallBack(clientId));        // 连接超时回调
        sseEmitter.onError(errorCallBack(clientId));          // 推送消息异常时，回调方法
        sseCache.put(clientId, sseEmitter);
        log.info("创建新的sse连接，当前用户：{}    累计用户:{}", clientId, sseCache.size());
        try {
            // 注册成功返回用户信息
            sseEmitter.send(SseEmitter.event().id(String.valueOf(HttpStatus.CREATED)).data(clientId, MediaType.APPLICATION_JSON));
        } catch (IOException e) {
            log.error("创建长链接异常，客户端ID:{}   异常信息:{}", clientId, e.getMessage());
        }
        return sseEmitter;
    }

    /**
     * 关闭连接
     *
     * @param clientId 客户端ID
     */
    public String closeConnect(String clientId) {
        SseEmitter sseEmitter = sseCache.get(clientId);
        if (sseEmitter != null) {
            sseEmitter.complete();
            removeUser(clientId);
        }
        return "成功";
    }

    /**
     * 推送消息到客户端
     * 此处做了推送失败后，重试推送机制，可根据自己业务进行修改
     **/
    public String sendMessage(SseMsgParams data) throws IOException {
        if (StringUtils.isEmpty(data.getToken())) {
            List<String> result = Lists.newArrayList();
            for (String e : sseCache.keySet()) {
                String r = sendMessage(e, data.getMessage());
                result.add(r);
            }
            return result.stream().collect(Collectors.joining(","));
        } else {
            return sendMessage(data.getToken(), data.getMessage());
        }
    }


    /**
     * 推送消息到客户端
     * 此处做了推送失败后，重试推送机制，可根据自己业务进行修改
     **/
    public String sendMessage(String token, String message) throws IOException {
        String clientId = token;
        SseEmitter sseEmitter = sseCache.get(clientId);
        if (sseEmitter == null) {
            log.error("推送消息失败：客户端{}未创建长链接,失败消息:{}", clientId, message);
            return "失敗";
        }
//        SseEmitter.SseEventBuilder sendData = SseEmitter.event().id(String.valueOf(HttpStatus.OK))
//                .data(data.getMessage(), MediaType.APPLICATION_JSON);
        try {
            sseEmitter.send(message);
        } catch (IOException e) {
            // 推送消息失败，记录错误日志，进行重推
            log.error("推送消息失败：{},尝试进行重推", "null");
            boolean isSuccess = true;
            // 推送消息失败后，每隔10s推送一次，推送5次
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(10000);
                    sseEmitter = sseCache.get(clientId);
                    if (sseEmitter == null) {
                        log.error("{}的第{}次消息重推失败，未创建长链接", clientId, i + 1);
                        continue;
                    }
                    sseEmitter.send(message);
                } catch (Exception ex) {
                    log.error("{}的第{}次消息重推失败", clientId, i + 1, ex);
                    continue;
                }
                log.info("{}的第{}次消息重推成功,{}", clientId, i + 1, "");
                return "推送結束";
            }
        }
        return "success";
    }


    public List<EmitterUserVo> allUser() {
        List<String> tokens = sseCache.keySet().stream().toList();
        return tokens.stream().map(e -> {
                    EmitterUserVo vo = new EmitterUserVo();
                    vo.setToken(e);
                    vo.setEmployeeNo(e);
                    return vo;
                })
                .collect(Collectors.toList());
    }


    /**
     * 长链接完成后回调接口(即关闭连接时调用)
     *
     * @param clientId 客户端ID
     **/
    private Runnable completionCallBack(String clientId) {
        return () -> {
            log.info("结束连接：{}", clientId);
            removeUser(clientId);
        };
    }

    /**
     * 连接超时时调用
     *
     * @param clientId 客户端ID
     **/
    private Runnable timeoutCallBack(String clientId) {
        return () -> {
            log.info("连接超时：{}", clientId);
            removeUser(clientId);
        };
    }

    /**
     * 推送消息异常时，回调方法
     *
     * @param clientId 客户端ID
     **/
    private Consumer<Throwable> errorCallBack(String clientId) {
        return throwable -> {
            log.error("SseEmitterServiceImpl[errorCallBack]：连接异常,客户端ID:{}", clientId);

            // 推送消息失败后，每隔10s推送一次，推送5次
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(10000);
                    SseEmitter sseEmitter = sseCache.get(clientId);
                    if (sseEmitter == null) {
                        log.error("SseEmitterServiceImpl[errorCallBack]：第{}次消息重推失败,未获取到 {} 对应的长链接", i + 1, clientId);
                        continue;
                    }
                    sseEmitter.send("失败后重新推送");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    /**
     * 移除用户连接
     *
     * @param clientId 客户端ID
     **/
    private void removeUser(String clientId) {
        sseCache.remove(clientId);
        log.info("SseEmitterServiceImpl[removeUser]:移除用户：{}", clientId);
    }

}
