package com.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter의 올바른 기능
 * 등록에 관한 사항 web.xml -> Filter 코드 참고
 * - 입증 (Header, Protocol 성립 조건 및 CORS 처리 조건 등)
 * - 로깅 및 감사 (어디서 어디로 들어가는지 등 로그처리)
 * - 이미지 및 데이터 압축 (이미지일 경우 해당 데이터 압축 처리)
 * - Spring MVC에서 분리하려는 모든 기능
 * - 서버에서 급하게 막을것들을 미리 막아줌 (Block)
 * <p>
 * 순서 로직
 * Filter Init -> Interceptor PostConstruct -> Filter doFilter(Pre Process) -> Interceptor preHandle ->
 * Interceptor postHandle -> Interceptor afterCompletion -> Filter(After Process)
 */
@Slf4j
public class GeneralFilter implements Filter {
    /**
     * 필터생성시 처리 로직
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("general filter init");
    }

    /**
     * GeneralFilter
     * Filter 실행 부분
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        filterChain.doFilter(servletRequest, servletResponse);
    }


    /**
     * Filter 종료 부분
     * 자원의 해제 처리
     */
    @Override
    public void destroy() {
        log.debug("general filter destroy");
    }
}
