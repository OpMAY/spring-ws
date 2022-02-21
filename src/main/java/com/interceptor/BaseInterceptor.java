package com.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class BaseInterceptor extends HandlerInterceptorAdapter {
    /**
     * Interceptor 사용할 경우
     * - 애플리케이션 로깅과 같은 교차 문제 처리
     * - 자세한 승인 확인 (권한 자세한 검사)
     * - Spring 컨텍스트 또는 모델 조작
     * <p>
     * 등록에 관련된 코드는 Dispatcher-servlet.xml Interceptor 부분 참조
     * <p>
     * Return false = interceptor chain stop
     * Return true = interceptor chain continue
     * PreHandler = Some Controller Before Start
     * PostHandler = Some Controller After Start
     * AfterCompletion = Some Controller After and View Complete
     * AfterConcurrentHandlingStarted = Asynchronous process (post,after func not executed)
     * <p>
     * 순서 로직
     * Filter Init -> Interceptor PostConstruct -> Filter doFilter(Pre Process) -> Interceptor preHandle ->
     * Interceptor postHandle -> Interceptor afterCompletion -> Filter(After Process)
     */

    @PostConstruct
    public void BaseInterceptor() {
        log.debug("Base Interceptor Post Constructor");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("Base Interceptor preHandle");
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.debug("Base Interceptor postHandle");
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.debug("Base Interceptor afterCompletion");
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("Base Interceptor afterConcurrentHandlingStarted");
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}
