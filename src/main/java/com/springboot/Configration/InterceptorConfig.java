package com.springboot.Configration;

import com.springboot.Interceptor.WheelInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Bean
    public WheelInterceptor getWheelInterceptor(){
        return new WheelInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(getWheelInterceptor());
        registration.addPathPatterns("/**");                      //所有路径都被拦截
        registration.excludePathPatterns(                         //添加不拦截路径
                "你的登陆路径",            //登录
                "/**/*.html",            //html静态资源
                "/**/*.js",              //js静态资源
                "/**/*.css",             //css静态资源
                "/**/*.woff",
                "/**/*.ttf",
                "/userManage/login",     //登录路径
                "/plan/export",             //导出表
                "/plan/import",             //导入表
                "/mob/login",               //移动端访问登录
                "/wheelqrcode/**",       //二维码
                "/brocast/**"
        );
    }
}
