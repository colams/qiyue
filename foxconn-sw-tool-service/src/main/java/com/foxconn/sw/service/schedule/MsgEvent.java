package com.foxconn.sw.service.schedule;

import com.foxconn.sw.business.message.SwMsgPoolBusiness;
import com.foxconn.sw.business.oa.SwTaskEmployeeRelationBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.common.utils.MailUtils;
import com.foxconn.sw.data.entity.SwEmployee;
import com.foxconn.sw.data.entity.SwMsgPool;
import com.foxconn.sw.data.entity.SwTaskEmployeeRelation;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static com.foxconn.sw.common.utils.DateTimeUtils.DateTimePattern.yyyyMMddHHmmsssss;

@Component
public class MsgEvent extends BaseScheduling {

    @Autowired
    SwMsgPoolBusiness msgPoolBusiness;
    @Autowired
    SwTaskEmployeeRelationBusiness taskEmployeeRelationBusiness;
    @Autowired
    EmployeeBusiness employeeBusiness;


    @Scheduled(cron = "0 * * * * *")
    public void cron() {
        String times = DateTimeUtils.format(LocalDateTime.now(), yyyyMMddHHmmsssss);
        System.out.println(times + "  MsgEvent start ------------");
        try {
            List<SwMsgPool> messages = msgPoolBusiness.getMsgPool2Process();
            messages.forEach(e -> {
                System.out.println("执行了一次：" + JsonUtils.serialize(e));
                Integer taskID = e.getObjectId();
                List<SwTaskEmployeeRelation> relations = taskEmployeeRelationBusiness.getRelationsByTaskId(taskID);
                relations.forEach(relation -> {
                    SwEmployee employee = employeeBusiness.selectEmployeeByENo(relation.getEmployeeNo());
                    if (StringUtils.isEmpty(employee.getInnerEmail())) {
                        System.out.println("邮箱 为空" + employee.getEmployeeNo());
                        return;
                    }
                    MailUtils.sendTaskNotifyEmail(Lists.newArrayList(employee.getInnerEmail()));
                });
            });
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(times + "  MsgEvent finish ------------");
    }

    @Override
    public String getCronTrigger() {
        return "0/5 * * * * ?";
    }

}
