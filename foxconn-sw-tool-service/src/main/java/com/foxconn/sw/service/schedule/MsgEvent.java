package com.foxconn.sw.service.schedule;

import com.foxconn.sw.business.message.SwMsgPoolBusiness;
import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.data.entity.SwMsgPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MsgEvent extends BaseScheduling {

    @Autowired
    SwMsgPoolBusiness msgPoolBusiness;


    @Scheduled(cron = "0/10 * * * * ?")
    public void cron() {
        try {
            System.out.println("MsgEvent start ------------");
            List<SwMsgPool> messages = msgPoolBusiness.getMsgPool2Process();
            messages.forEach(e -> {
                System.out.println("执行了一次：" + JsonUtils.serialize(e));
            });
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("MsgEvent finish ------------");
    }

    @Override
    public String getCronTrigger() {
        return "0/5 * * * * ?";
    }

}
