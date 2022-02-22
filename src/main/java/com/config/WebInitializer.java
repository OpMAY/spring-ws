package com.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.util.EnumSet;

@Slf4j
public class WebInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext container) {
        log.info("WebInitializer : starting");

        // init properties
        container.setInitParameter("contextInitializerClasses", "com.config.PropertyConfig");

        // init dispatcher servlet
        ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", new DispatcherServlet());
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

        // root config
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        container.addListener(new ContextLoaderListener(context));

        // 인코딩 필터 적용
        FilterRegistration.Dynamic charaterEncodingFilter = container.addFilter("charaterEncodingFilter", new CharacterEncodingFilter());
        charaterEncodingFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        charaterEncodingFilter.setInitParameter("encoding", "UTF-8");
        charaterEncodingFilter.setInitParameter("forceEncoding", "true");
        log.info("WebInitializer : finished");
    }
}