package com.matheus.dnsipmanager.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired private AuthInterceptor authInterceptor;
    @Override public void addInterceptors(InterceptorRegistry registry){registry.addInterceptor(authInterceptor).addPathPatterns("/api/**");}
    @Override public void addCorsMappings(CorsRegistry r){r.addMapping("/api/**").allowedOrigins("*").allowedMethods("*");}
}
