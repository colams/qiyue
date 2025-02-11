package com.foxconn.sw.service.schedule;

import com.foxconn.sw.business.message.SwMsgPoolBusiness;
import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.data.entity.SwMsgPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class MsgEvent extends BaseScheduling {

    @Autowired
    SwMsgPoolBusiness msgPoolBusiness;


    @Scheduled(cron = "0/10 * * * * ?")
    public void cron() {
        try {
//            SseMsgParams sseMsg = new SseMsgParams();
//            sseMsg.setToken("6990cf716a854a49b0dca0165c726c4e");
//            sseMsg.setMessage("測試消息");
//            String result = emitterProcessor.sendMessage(sseMsg);
//            System.out.println("执行了一次：" + result);


            System.out.println("执行了一次：" + DateTimeUtils.format(LocalDateTime.now()));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private List<SwMsgPool> getMsgList() {
        return msgPoolBusiness.getMsgPools();
    }


    @Override
    public String getCronTrigger() {
        return "0/5 * * * * ?";
    }

}
