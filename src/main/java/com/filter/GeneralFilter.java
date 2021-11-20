package com.filter;

import lombok.extern.log4j.Log4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
@Log4j
public class GeneralFilter implements Filter {
    private String contextPath;
    private List<String> excludedUrls;
    /*public static boolean check = true;*/

    /**
     * 필터생성시 처리 로직
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("general filter init");
        contextPath = filterConfig.getServletContext().getContextPath();
        String excludePattern = filterConfig.getInitParameter("excludedUrls");
        excludedUrls = Arrays.asList(excludePattern.split(","));
    }

    /**
     * GeneralFilter
     * Filter 실행 부분
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        /**
         * 다음 Filter 실행 전 처리 : 개발버전 일시 해당 부분 전체 주석 처리
         * */
        /*log.debug("doFilter pre process");
        if (check) {
            check = false;
            *//**해당 /test/cdn.do에 부합하는 Controller 로직을 다시 태워준다.
         * [2021-08-23 15:11:04] INFO : com.filter.GeneralFilter - doFilter pre process (처음 URI)
         * [2021-08-23 15:11:04] INFO : com.filter.GeneralFilter - doFilter after process (처음 URI)
         * [2021-08-23 15:11:04] INFO : com.filter.GeneralFilter - doFilter pre process (다음 URI : /test/cdn.do)
         * [2021-08-23 15:11:04] DEBUG: org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping - Mapped to com.controller.HomeController#cdnService()
         * *//*
            res.sendRedirect(contextPath + "/test/cdn.do");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }*/
        if (!excludedUrls.contains(req.getServletPath())) {
            // 제외하지 않는 경우
            // 로직을 타게 합니다.
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            /**Error.do는 급하게 URL 막는 경우 사용되는 URL 사용할려면 미리 만들어 놓아야한다.*/
            res.sendRedirect(contextPath + "/error.do");
        }
        /**
         * 다음 Filter 실행 후 처리
         * */
        log.debug("doFilter after process");
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
