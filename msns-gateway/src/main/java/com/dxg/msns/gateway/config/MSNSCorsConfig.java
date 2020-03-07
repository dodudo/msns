package com.dxg.msns.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class MSNSCorsConfig {
    @Bean
    public CorsFilter corsFilter(){
        //添加cors配置信息
        CorsConfiguration config = new CorsConfiguration();
        //添加允许的域，不要写“*”，否则cookie无法使用
        config.addAllowedOrigin("http://localhost:8080");
        //是否发送cookie信息
        config.setAllowCredentials(true);
        //添加允许的请求方式
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        //允许的头信息
        config.addAllowedHeader("*");

        //添加映射路径，我们拦截一切请求
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);

        //返回新的CorsFilter.
        return new CorsFilter(configSource);

    }
}
