package com.foxconn.sw.service.schedule;

import com.foxconn.sw.business.SwConfigDicBusiness;
import com.foxconn.sw.business.message.SwMsgPoolBusiness;
import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.business.oa.SwTaskEmployeeRelationBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.data.entity.SwEmployee;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MeetingNotifyJob extends CustomSchedulingConfig {

    private static final Logger log = LoggerFactory.getLogger(MeetingNotifyJob.class);

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
            // todo

        } catch (Exception e) {
            log.error("MsgEvent.cron", e);
        }
        log.info("  {} finish ------------", getJobName());
    }

    private void processMsg() {
        // todo notify
    }

    private String getCurrentUser(SwEmployee employee) {
        return String.format("%s/%s", employee.getEmployeeNo(), employee.getName());
    }

    private String getSubject(SwEmployee employee, Long taskNo) {
        return String.format("[OA Platform] %s:您有新的會議即將開始",
                getCurrentUser(employee),
                taskNo);
    }

    private String getContent(SwEmployee employee, Long taskNo) {
        return String.format("尊敬的 %s OA用戶:\n" +
                "\n<br/>" +
                "您有新的會議即將開始 \n" +
                "\n<br/>" +
                "1、您可以<a href='https://rayplusoa.efoxconn.com/'>點擊這裡</a>進入系統查看會議", getCurrentUser(employee), taskNo);
    }

    @Override
    String getJobName() {
        return "MeetingNotifyJob";
    }

    @Override
    public String getCronTrigger() {
        String cronExpression = configDicBusiness.getConfigDicValue("msg.event.cron");
        if (StringUtils.isEmpty(cronExpression)) {
            return "0 0/10 * * * *";
        }
        return cronExpression;
    }
}
