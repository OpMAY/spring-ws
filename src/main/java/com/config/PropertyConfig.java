package com.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.util.EncryptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.web.context.ConfigurableWebApplicationContext;

@Slf4j
public class PropertyConfig implements ApplicationContextInitializer<ConfigurableWebApplicationContext> {
    public void initialize(ConfigurableWebApplicationContext ctx) {
        log.info("PropertyConfig : initializing");
        Map<String, Object> properties = null;
        Map<String, Object> en_properties = new HashMap<>();
        EncryptionService encryptionService = new EncryptionService();
        try {
            ResourcePropertySource propertySource = new ResourcePropertySource(new ClassPathResource("/config.properties"));
            ctx.getEnvironment().getPropertySources().addLast(propertySource);
            log.info("Added Config Properties");
            propertySource = new ResourcePropertySource(new ClassPathResource("/key.properties"));
            properties = propertySource.getSource();
            properties.forEach((key, value) -> {
                try {
                    en_properties.put(key, encryptionService.decryptAES((String) value));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            ctx.getEnvironment().getPropertySources().addLast(new MapPropertySource("props", en_properties));
            log.info("Added Key Properties");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            log.info("PropertyConfig : initialized");
        }
    }
}