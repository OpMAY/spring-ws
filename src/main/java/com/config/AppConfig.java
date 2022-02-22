package com.config;

import com.util.FileDownload;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer { // WebMvcConfigurer: for interceptor

    @Bean // view resolver
    public ViewResolver configureViewResolvers() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean // custom view: view가 없을 경우 커스텀 지정한 class를 찾도록 설정
    public ViewResolver beanNameViewResolver(){
        BeanNameViewResolver resolver = new BeanNameViewResolver();
        resolver.setOrder(0);
        return resolver;
    }
    @Bean // 파일 다운로드 빈 등록
    public FileDownload fileDownload() {
        return new FileDownload();
    }

    @Bean // 파일 업로드 설정
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("utf-8");
        multipartResolver.setMaxUploadSize(26214400); // 전체 최대 25mb
        multipartResolver.setMaxUploadSizePerFile(5242880); // 각 최대 5mb
        return multipartResolver;
    }

    /*@Bean
    public BaseInterceptor baseInterceptor() {
        return new BaseInterceptor();
    }*/


    /*@Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(baseInterceptor())
                .addPathPatterns("/**")
                .addPathPatterns("/**.do");
//                .excludePathPatterns("/resources/**");
    }*/

    @Override // 정적 리소스 매핑
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/files/**").addResourceLocations("/files/");
        registry.addResourceHandler("/favicon.ico").addResourceLocations("/resources/assets/meta/favicon.ico");
    }
}
