package com.imooc.order.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 定时任务
 */
@Configuration
@EnableScheduling
public class TaskSchedulerConfig implements SchedulingConfigurer {
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(this.taskScheduled());//指定线程池
    }
    /**
     *定义线程池数
     * @return
     */
    @Bean(destroyMethod = "shutdown")
    public Executor taskScheduled(){
        return Executors.newScheduledThreadPool(100);

    }

}