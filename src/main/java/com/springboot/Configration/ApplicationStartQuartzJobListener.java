package com.springboot.Configration;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
//@Configuration
public class ApplicationStartQuartzJobListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private MyScheduler myScheduler;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
            myScheduler.startJob();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
