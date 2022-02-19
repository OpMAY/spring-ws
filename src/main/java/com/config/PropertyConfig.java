package com.config;

import java.io.IOException;

import lombok.extern.log4j.Log4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.web.context.ConfigurableWebApplicationContext;

@Log4j
public class PropertyConfig implements ApplicationContextInitializer<ConfigurableWebApplicationContext> {
    public void initialize(ConfigurableWebApplicationContext ctx) {
        log.debug("PropertyConfig : initialize start");
        PropertySource propertySource = null;
        try {
            ctx.getEnvironment().getPropertySources().addLast(new ResourcePropertySource(new ClassPathResource("/config.properties")));
            ctx.getEnvironment().getPropertySources().addLast(new ResourcePropertySource(new ClassPathResource("/key.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.debug("PropertyConfig : initialize end");
    }
}