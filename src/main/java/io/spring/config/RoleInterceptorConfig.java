package io.spring.config;

import io.spring.interceptor.RoleInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class RoleInterceptorConfig implements WebMvcConfigurer {
    @Autowired
    RoleInterceptor roleInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(roleInterceptor)
                .addPathPatterns("/admin/*")
                .excludePathPatterns("/admin/signin");
    }
}
