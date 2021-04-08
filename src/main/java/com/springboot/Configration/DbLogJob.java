package com.springboot.Configration;

import com.springboot.service.VisitLogService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class DbLogJob implements Job {
    @Autowired
    private VisitLogService visitLogService;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        visitLogService.getLogAll("hehe");
    }
}
