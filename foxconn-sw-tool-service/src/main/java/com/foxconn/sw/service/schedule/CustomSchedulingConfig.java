package com.foxconn.sw.service.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.Date;

@EnableScheduling
public abstract class CustomSchedulingConfig implements SchedulingConfigurer {


    private static final Logger log = LoggerFactory.getLogger(CustomSchedulingConfig.class);

    @Override
    public void configureTasks(ScheduledTaskRegistrar registrar) {

        // 创建自定义触发器实例
        Trigger customTrigger = new CustomTrigger(getCronTrigger());

        // 添加任务和触发器到任务注册器
        registrar.addTriggerTask(() -> {
            log.info("{} Task executed at: {}", getJobName(), new Date());
            cron();
        }, customTrigger);
    }

    abstract void cron();

    abstract String getCronTrigger();

    abstract String getJobName();
}