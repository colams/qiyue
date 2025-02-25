package com.foxconn.sw.service.schedule;

import com.foxconn.sw.business.SwConfigDicBusiness;
import com.foxconn.sw.business.message.SwMsgPoolBusiness;
import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.business.oa.SwTaskEmployeeRelationBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.common.constanst.Constants;
import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.common.utils.MailUtils;
import com.foxconn.sw.data.entity.SwConfigDic;
import com.foxconn.sw.data.entity.SwEmployee;
import com.foxconn.sw.data.entity.SwMsgPool;
import com.foxconn.sw.data.entity.SwTaskEmployeeRelation;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class MsgEvent extends CustomSchedulingConfig {

    private static final Logger log = LoggerFactory.getLogger(MsgEvent.class);

    @Autowired
    SwMsgPoolBusiness msgPoolBusiness;
    @Autowired
    SwTaskEmployeeRelationBusiness taskEmployeeRelationBusiness;
    @Autowired
    EmployeeBusiness employeeBusiness;
    @Autowired
    SwConfigDicBusiness configDicBusiness;
    @Autowired
    SwTaskBusiness taskBusiness;


    @Override
    public void cron() {
        log.info("  {} start ------------", getJobName());
        try {
            List<SwMsgPool> messages = msgPoolBusiness.getMsgPool2Process();
            SwConfigDic configDic = configDicBusiness.queryConfigDic("create.task.msg.send");
            boolean isClose = Objects.isNull(configDic) || "0".equalsIgnoreCase(configDic.getItemValue());
            messages.forEach(e -> {
                log.info("执行了一次：{}", JsonUtils.serialize(e));
                if (isClose) {
                    msgPoolBusiness.closeMsg(e);
                } else {
                    processMsg(e);
                }
            });
        } catch (Exception e) {
            log.error("MsgEvent.cron", e);
        }
        log.info("  {} finish ------------", getJobName());
    }

    private void processMsg(SwMsgPool msgPool) {
        Long taskNo = msgPool.getObjectId();
        List<SwTaskEmployeeRelation> relations = taskEmployeeRelationBusiness.getRelationsByTaskNo(taskNo);
        relations.forEach(relation -> {
            SwEmployee employee = employeeBusiness.selectEmployeeByENo(relation.getEmployeeNo());
            if (StringUtils.isEmpty(employee.getInnerEmail()) || Constants.Hyphen.equalsIgnoreCase(employee.getInnerEmail())) {
                log.info("邮箱 为空：{}", employee.getEmployeeNo());
                return;
            }
            String subject = getSubject(employee, taskNo);
            String content = getContent(employee, taskNo);
            MailUtils.sendHtmlEmail(subject, content, Lists.newArrayList(employee.getInnerEmail()));
        });
    }

    private String getCurrentUser(SwEmployee employee) {
        return String.format("%s/%s", employee.getEmployeeNo(), employee.getName());
    }

    private String getSubject(SwEmployee employee, Long taskNo) {
        return String.format("[OA Platform] %s:您有新的工作任務需要處理,任務編號：%s",
                getCurrentUser(employee),
                taskNo);
    }

    private String getContent(SwEmployee employee, Long taskNo) {
        return String.format("尊敬的 %s OA用戶:\n" +
                "\n<br/>" +
                "您有新的工作任務需要處理, 任務編號：%s\n" +
                "\n<br/>" +
                "1、您可以<a href='https://rayplusoa.efoxconn.com/'>點擊這裡</a>進入系統完成處理", getCurrentUser(employee), taskNo);
    }

    @Override
    String getJobName() {
        return "MsgEvent";
    }

    @Override
    public String getCronTrigger() {
        String cronExpression = configDicBusiness.getConfigDicValue("msg.event.cron");
        if (StringUtils.isEmpty(cronExpression)) {
            return "0 0/5 * * * *";
        }
        return cronExpression;
    }
}
