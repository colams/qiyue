package com.foxconn.sw.service.schedule;


import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.time.Instant;
import java.util.Date;

@EnableScheduling
public abstract class BaseScheduling implements SchedulingConfigurer {
    @Override
    public void configureTasks(ScheduledTaskRegistrar registrar) {

        registrar.addTriggerTask(new Runnable() {
            @Override
            public void run() {
                cron();
            }
        }, new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                CronTrigger trigger = new CronTrigger(getCronTrigger());
                Date date = trigger.nextExecutionTime(triggerContext);
                return date;
            }

            @Override
            public Instant nextExecution(TriggerContext triggerContext) {
                return null;
            }
        });

    }

    public String getCronTrigger() {
        return "0/10 * * * * ?";
    }

    abstract void cron();

}