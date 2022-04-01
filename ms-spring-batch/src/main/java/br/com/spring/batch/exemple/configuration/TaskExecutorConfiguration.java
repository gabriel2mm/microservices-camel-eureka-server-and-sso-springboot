package br.com.spring.batch.exemple.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class TaskExecutorConfiguration {

    private static final Integer NUMBER_OF_THREADS= 4;
    private static final Integer NUMBER_OF_CORE_MAX = 8;

    @Bean(name = "myTaskExecutor")
    public TaskExecutor threadPoolTaskExecutor(){
        System.out.println("pool inicializado");
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setMaxPoolSize(NUMBER_OF_CORE_MAX);
        taskExecutor.setCorePoolSize(NUMBER_OF_THREADS);
        taskExecutor.setQueueCapacity(NUMBER_OF_CORE_MAX);
        taskExecutor.setThreadNamePrefix("ProcessJob-");
        taskExecutor.afterPropertiesSet();
        taskExecutor.initialize();

        return taskExecutor;
    }
}
