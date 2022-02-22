package com.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Slf4j
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AopConfig {
    public AopConfig() {
        log.info("Aop Initializing");
        log.info("Aop Initialized");
    }
}
