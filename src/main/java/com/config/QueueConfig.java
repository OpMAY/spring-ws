package com.config;

import com.model.SplitFileData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.PriorityQueue;

@Configuration
@Slf4j
public class QueueConfig {
    public QueueConfig() {
        log.info("Queue Initialized");
    }

    @Bean
    public synchronized HashMap<String, PriorityQueue<SplitFileData>> splitFileStorage() {
        log.info("SplitFileStorage initialized");
        return new HashMap<>();
    }

    @Bean
    public synchronized PriorityQueue<SplitFileData> mergeFileStorage() {
        log.info("MergeFileStorage initialized");
        return new PriorityQueue<>();
    }
}
