package com.assessment.payment.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
@EnableScheduling
public class TaskExecutorConfig {

    @Value("${spring.threads.virtual.enabled:false}")
    private Boolean virtualThreadsEnabled;

    @Bean
    TaskExecutor applicationTaskExecutor() {
        if(virtualThreadsEnabled) {
            var executor = new SimpleAsyncTaskExecutor();
            executor.setThreadNamePrefix("VirtualTask-");
            executor.setVirtualThreads(true);
            return executor;
        } else {
            ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
            executor.setCorePoolSize(5);
            executor.setMaxPoolSize(10);
            executor.setQueueCapacity(25);
            executor.setThreadNamePrefix("NormalTask-");
            executor.initialize();
            return executor;
        }
    }
}
