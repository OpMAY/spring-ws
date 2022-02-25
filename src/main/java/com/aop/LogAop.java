package com.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
@RequiredArgsConstructor
public class LogAop {

    private final RequestLogger requestLogger;
    private final HttpServletRequest request;

    private static final String PREFIX_IN = "--> ";
    private static final String PREFIX_OUT = "<-- ";
    private static final String PREFIX_EX = "<x- ";

    /**
     * Execution Expression Samples <br>
     * (return type) (class path).(method name)(params) <br>
     * ex) (*) (hello.core.service.). (*)(..) <br>
     * {@code @annotation(org.springframework.web.bind.annotation.GetMapping)} 해당 어노테이션이 달린 메소드들 매핑
     */
    @Around("execution(* com.controller..*(..)) && !execution(* com.controller.WebSocketController.*(..))")
    public Object AroundController(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            requestLogger.setRequestURL(request.getRequestURI());
            requestLogger.log(PREFIX_IN, joinPoint);
            Object result = joinPoint.proceed();
            requestLogger.log(PREFIX_OUT, joinPoint);
            return result;
        } catch (Exception e) {
            requestLogger.log(PREFIX_EX, joinPoint);
            throw e;
        }
    }

    @Around("execution(* com.service..*(..))")
    public Object AroundService(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            requestLogger.log(PREFIX_IN, joinPoint);
            long start_time = System.currentTimeMillis();
            Object result = joinPoint.proceed();
            long end_time = System.currentTimeMillis();
            requestLogger.log(end_time - start_time, PREFIX_OUT, joinPoint);
            return result;
        } catch (Exception e) {
            requestLogger.log(PREFIX_EX, joinPoint);
            throw e;
        }
    }
}
