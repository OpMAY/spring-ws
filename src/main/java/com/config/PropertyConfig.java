package com.config;

import com.util.Constant;
import com.util.Encryption.EncryptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.web.context.ConfigurableWebApplicationContext;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class PropertyConfig implements ApplicationContextInitializer<ConfigurableWebApplicationContext> {
    public void initialize(ConfigurableWebApplicationContext ctx) {
        log.info("PropertyConfig : initializing");
        Map<String, Object> properties = null;
        Map<String, Object> en_properties = new HashMap<>();
        Map<String, Object> db_properties = new HashMap<>();
        EncryptionService encryptionService = new EncryptionService();
        try {
            ResourcePropertySource propertySource = new ResourcePropertySource(new ClassPathResource("/config.properties"));
            properties = propertySource.getSource();
            properties.forEach((key, value) -> {
                /**
                 * PR
                 * @Date 2022-07-26
                 * @Author kimwoosik
                 * @Description
                 * Database USERNAME, PASSWORD, URL Encrypt -> Decrypt
                 * */
                try {
                    if (key.contains("DATABASE." + Constant.DatabaseSetting.DATABASE_SOURCE)) {
                        log.info("DATABASE Property Origin -> {},{}", key, value);
                        log.info("DATABASE Property Decrypt -> {},{}", key, encryptionService.decryptAES((String) value));
                        if (key.contains("USERNAME")) {
                            db_properties.put("DATABASE_USERNAME", encryptionService.decryptAES((String) value));
                        } else if (key.contains("PASSWORD")) {
                            db_properties.put("DATABASE_PASSWORD", encryptionService.decryptAES((String) value));
                        } else if (key.contains("URL")) {
                            db_properties.put("DATABASE_URL", encryptionService.decryptAES((String) value));
                        }
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            ctx.getEnvironment().getPropertySources().addLast(new MapPropertySource("db_props", db_properties));
            log.info("Added DB Props Properties");

            propertySource = new ResourcePropertySource(new ClassPathResource("/config.properties"));
            ctx.getEnvironment().getPropertySources().addLast(propertySource);
            log.info("Added Config Properties");

            propertySource = new ResourcePropertySource(new ClassPathResource("/key.properties"));
            properties = propertySource.getSource();
            properties.forEach((key, value) -> {
                try {
                    log.info(key + " = " + encryptionService.decryptAES((String) value));
                    en_properties.put(key, encryptionService.decryptAES((String) value));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            ctx.getEnvironment().getPropertySources().addLast(new MapPropertySource("props", en_properties));
            log.info("Added Key Properties");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            log.info("PropertyConfig : initialized");
        }
    }
}