package com.foxconn.sw.service.schedule;

import com.foxconn.sw.business.SwConfigDicBusiness;
import com.foxconn.sw.business.message.SwMsgPoolBusiness;
import com.foxconn.sw.business.oa.SwTaskEmployeeRelationBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.common.constanst.Constants;
import com.foxconn.sw.common.constanst.NumberConstants;
import com.foxconn.sw.common.utils.MailUtils;
import com.foxconn.sw.data.entity.SwEmployee;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WorkReportJob extends CustomSchedulingConfig {

    private static final Logger log = LoggerFactory.getLogger(WorkReportJob.class);

    @Autowired
    SwMsgPoolBusiness msgPoolBusiness;
    @Autowired
    SwTaskEmployeeRelationBusiness taskEmployeeRelationBusiness;
    @Autowired
    EmployeeBusiness employeeBusiness;
    @Autowired
    SwConfigDicBusiness configDicBusiness;


    @Override
    public void cron() {
        log.info("  WorkReportJob  ------------  start");

        String subject = "[OA Platform] 週報提交提醒";
        String content = "【週報提醒】請按時提交本週工作總結及下週計劃\n\n<br/> 1、您可以<a href='https://rayplusoa.efoxconn.com/'>點擊這裡</a>進入系統完成處理";

        try {
            List<SwEmployee> employees = employeeBusiness.getEmployeeList();
            List<String> recipients = employees.stream()
                    .filter(e -> NumberConstants.ZERO.equals(e.getStatus()))
                    .filter(e -> StringUtils.isNotEmpty(e.getInnerEmail()))
                    .filter(e -> !Constants.Hyphen.equalsIgnoreCase(e.getInnerEmail()))
                    .map(SwEmployee::getInnerEmail)
                    .collect(Collectors.toList());

            recipients.forEach(e -> {
                MailUtils.sendHtmlEmail(subject, content, Lists.newArrayList(e));
            });
        } catch (Exception e) {
            log.error("MsgEvent.cron", e);
        }
        log.info("  WorkReportJob  ------------  end");
    }

    @Override
    String getJobName() {
        return "WorkReportJob";
    }

    @Override
    public String getCronTrigger() {
        String cronExpression = configDicBusiness.getConfigDicValue("work.report.cron");
        if (StringUtils.isEmpty(cronExpression)) {
            return "0/10 * 8 * * 5";
        }
        return cronExpression;
    }

}
