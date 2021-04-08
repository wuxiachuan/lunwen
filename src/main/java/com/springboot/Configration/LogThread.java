package com.springboot.Configration;

import com.springboot.dao.VisitLogDao;
import com.springboot.service.VisitLogService;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogThread implements DisposableBean,Runnable {
    private Thread thread;
    private volatile boolean run = true;
    private VisitLogService visitLogService;
    @Autowired
    public LogThread(VisitLogService visitLogService) {
        this.visitLogService = visitLogService;
        this.thread  = new Thread(this);
        this.thread.start();
        System.out.println("thread start");
    }

    @Override
    public void run() {
        while (run){
            try {
                Thread.sleep(1000*60*3);
                visitLogService.storeLog2Db();
                System.out.println("log store to db!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void destroy() throws Exception {
        run = false;
        System.out.println("stop");
    }
}
