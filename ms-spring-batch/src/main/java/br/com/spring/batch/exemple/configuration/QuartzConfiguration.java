package br.com.spring.batch.exemple.configuration;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfiguration {

    @Bean
    public JobDetail jobDetail(){
        return JobBuilder
                .newJob(ScheduleConfiguration.class)
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger trigger(){
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder
                .simpleSchedule()
                .withIntervalInMinutes(1)
                .withRepeatCount(2);

        return TriggerBuilder
                .newTrigger()
                .forJob(jobDetail())
                .withSchedule(simpleScheduleBuilder)
                .build();
    }
}