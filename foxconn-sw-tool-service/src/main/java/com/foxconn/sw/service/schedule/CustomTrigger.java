package com.foxconn.sw.service.schedule;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.support.CronTrigger;

import java.time.Instant;
import java.util.Date;

public class CustomTrigger implements Trigger {


    private CronTrigger cronTrigger;

    // 构造函数，接收 Cron 表达式
    public CustomTrigger(String cronExpression) {
        this.cronTrigger = new CronTrigger(cronExpression);
    }

    public void setCronTrigger(String cronExpression) {
        this.cronTrigger = new CronTrigger(cronExpression);
    }

    // 重写 nextExecutionTime 方法，使用 CronTrigger 计算下一次执行时间
    @Override
    public Date nextExecutionTime(TriggerContext triggerContext) {
        return cronTrigger.nextExecutionTime(triggerContext);
    }

    // 重写 nextExecution 方法，调用 nextExecutionTime 方法并转换为 Instant
    @Override
    public Instant nextExecution(TriggerContext triggerContext) {
        Date nextExecutionTime = nextExecutionTime(triggerContext);
        return nextExecutionTime != null ? nextExecutionTime.toInstant() : null;
    }

}
