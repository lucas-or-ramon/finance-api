package br.com.devcanoa.finance.api.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class TaskExecutorConfig {

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadFactory(new CustomizableThreadFactory("last-twelve-months-"));
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(80);
        executor.setKeepAliveSeconds(1);
        TaskExecutor taskExecutor = new ConcurrentTaskExecutor(executor);
        executor.initialize();
        return taskExecutor;
    }
}
