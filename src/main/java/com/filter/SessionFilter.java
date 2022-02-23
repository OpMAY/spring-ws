package com.filter;

import com.util.Constant;
import com.util.Encryption.EncryptionService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;


/**
 * Session Filter JWT 가볍게 검사 및 Version 검사하여 세션 자원 해제
 */
@Slf4j
public class SessionFilter implements Filter {
    private String contextPath;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("Session filter init");
        contextPath = filterConfig.getServletContext().getContextPath();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        if (req.getSession().getAttribute("jwt") != null) {
            /**Login 했을 때의 Session 필터링*/
            HashMap<String, Object> hashMap = new EncryptionService().decryptJWT(req.getSession().getAttribute("jwt").toString());
            String version = (String) hashMap.get("version");
            if (Objects.equals(version, Constant.VERSION))
                filterChain.doFilter(servletRequest, servletResponse);
            else {
                req.getSession().removeAttribute("jwt");
                filterChain.doFilter(servletRequest, servletResponse);
                /*res.sendRedirect(contextPath + "/");*/
            }
        } else {
            /**Login 하지 않았을 때의 Session 필터링*/
            /*res.sendRedirect(contextPath + "/login.do");*/
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }


    /**
     * Filter 종료 부분
     * 자원의 해제 처리
     */
    @Override
    public void destroy() {
        log.debug("Session filter destroy");
    }
}
