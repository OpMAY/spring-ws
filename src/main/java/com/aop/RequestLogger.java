package com.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

/**
 * request logger는 Bean의 Scope를 "request"로 설정함에 따라 각 request 마다 uuid를 생성하여 로그를 출력해줍니다. <br>
 * Log Form: [uuid][time(서비스의 경우)] returnType class.method()
 */


@Component
@Slf4j
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        log.info("[{}][{}]{}", uuid, requestURL, message);
    }

    public void log(String prefix, ProceedingJoinPoint joinPoint) {
        log.info("[{}][{}]{}{}", uuid, requestURL, prefix, joinPoint.getSignature());
    }

    public void log(long time, String prefix, ProceedingJoinPoint joinPoint) {
        log.info("[{}][{}][{}]{}{}", uuid, requestURL, time, prefix, joinPoint.getSignature());
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString().substring(0, 8);
        log.info("[{}] request created", uuid);
    }

    @PreDestroy
    public void close() {
        log.info("[{}] request closed", uuid);
    }
}
