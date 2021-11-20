package com.schedule;

import lombok.extern.log4j.Log4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Log4j
@Component
@EnableScheduling
public class Schedule {
    @Scheduled(cron = "*/600 * * * * *")
    public void Schedule() throws InterruptedException {
        /* System Scheduling or Database Scheduling Develop here*/
        log.info("================Task has been stared===============");
        log.info("Task execute 600 seconds" + new Date());
        log.info("================Task has been ended===============");
    }

    //@Scheduled(cron = "*/30 * * * * *")
    /*public void ScheduleTask() throws InterruptedException {
     *//** System Scheduling or Database Scheduling Develop here*//*
        log.info("================Task has been stared===============");
        log.info("Task execute 30 seconds" + new Date());
        Thread.sleep(3000L);
        log.info("================Task has been ended===============");
    }*/
}
