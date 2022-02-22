package com.config;

import com.util.FileDownload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebMvc
@Slf4j
public class AppConfig implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("configureMessageConverters");
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
        stringConverter.setWriteAcceptCharset(true);
        MediaType mediaType = new MediaType("text", "html", StandardCharsets.UTF_8);
        List<MediaType> types = new ArrayList<>();
        types.add(mediaType);
        stringConverter.setSupportedMediaTypes(types);
        converters.add(stringConverter);
        converters.add(new SourceHttpMessageConverter<>());
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setPrettyPrint(true);
        converters.add(converter);
    }

    @Bean // view resolver
    public ViewResolver configureViewResolvers() {
        log.info("configureViewResolvers : initializing");
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        log.info("configureViewResolvers : initialized");
        return viewResolver;
    }

    @Bean // custom view: view가 없을 경우 커스텀 지정한 class를 찾도록 설정
    public ViewResolver beanNameViewResolver() {
        log.info("beanNameViewResolver : initializing");
        BeanNameViewResolver resolver = new BeanNameViewResolver();
        resolver.setOrder(0);
        log.info("beanNameViewResolver : initialized");
        return resolver;
    }

    @Bean // 파일 다운로드 빈 등록
    public FileDownload fileDownload() {
        return new FileDownload();
    }

    @Bean // 파일 업로드 설정
    public CommonsMultipartResolver multipartResolver() {
        log.info("multipartResolver : initializing");
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("utf-8");
        multipartResolver.setMaxUploadSize(26214400); // 전체 최대 25mb
        multipartResolver.setMaxUploadSizePerFile(5242880); // 각 최대 5mb
        log.info("multipartResolver : initialized");
        return multipartResolver;
    }

    @Override // 정적 리소스 매핑
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("addResourceHandlers : initializing");
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/files/**").addResourceLocations("/files/");
        registry.addResourceHandler("/favicon.ico").addResourceLocations("/resources/assets/meta/favicon.ico");
        log.info("addResourceHandlers : initialized");
    }
}
