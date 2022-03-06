package com.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableScheduling
public class Schedule {
    private static final long SCHEDULE_VARIABLE = 1000000;
    private static final long SCHEDULE_VARIABLE_TEST1 = 10000;
    private static final long SCHEDULE_VARIABLE_TEST2 = 10000;
    private static final long SCHEDULE_VARIABLE_TEST3 = 10000;
    private static final long SCHEDULE_VARIABLE_TEST4 = 1000;

    @Scheduled(fixedDelay = SCHEDULE_VARIABLE)
    public void ScheduleTest1() {
        /** System Scheduling or Database Scheduling Develop here*/
    }

    @Scheduled(fixedDelay = SCHEDULE_VARIABLE_TEST1)
    public void ScheduleTest2() {
        /** System Scheduling or Database Scheduling Develop here*/
    }

    @Scheduled(cron = "*/600 * * * * *")
    public void Schedule() throws InterruptedException {
        /* System Scheduling or Database Scheduling Develop here*/
//        log.info("================Task has been stared===============");
//        log.info("Task execute 600 seconds" + new Date());
//        log.info("================Task has been ended===============");
    }

    //@Scheduled(cron = "*/30 * * * * *")
    /*public void ScheduleTask() throws InterruptedException {
     *//** System Scheduling or Database Scheduling Develop here*//*
        log.info("================Task has been stared===============");
        log.info("Task execute 30 seconds" + new Date());
        Thread.sleep(3000L);
        log.info("================Task has been ended===============");
    }*/

    @Scheduled(fixedRate = 30 * 1000)
    @CacheEvict(value = "IG", allEntries = true)
    public void evictCache() {
        /** System Scheduling or Database Scheduling Develop here*/
    }
}
