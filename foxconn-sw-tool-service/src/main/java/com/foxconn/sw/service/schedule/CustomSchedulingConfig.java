package com.foxconn.sw.service.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

@EnableScheduling
public abstract class CustomSchedulingConfig implements SchedulingConfigurer {


    private static final Logger log = LoggerFactory.getLogger(CustomSchedulingConfig.class);
    // 使用 AtomicReference 来存储当前的触发器
    private final AtomicReference<Trigger> currentTrigger = new AtomicReference<>();
    // 使用 AtomicReference 来存储当前的 cron 表达式
    private final AtomicReference<String> currentCron = new AtomicReference<>();

    @Override
    public void configureTasks(ScheduledTaskRegistrar registrar) {

        // 初始化触发器
        String cron = getCronTrigger();
        CustomTrigger customTrigger = new CustomTrigger(cron);
        // 添加任务和触发器到任务注册器
        registrar.addTriggerTask(() -> {
            try {
                // 重新获取 cron 表达式
                String newCron = getCronTrigger();
                // 检查 cron 表达式是否有变化
                if (!newCron.equals(cron)) {
                    customTrigger.setCronTrigger(newCron);
                }
                log.info("{} Task executed at: {},{}", getJobName(), new Date(), newCron);
                cron();
            } catch (Exception e) {
                log.error("Error executing task {}: {}", getJobName(), e.getMessage(), e);
            }
        }, customTrigger);
    }

    abstract void cron();

    abstract String getCronTrigger();

    abstract String getJobName();
}