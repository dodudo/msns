package com.dxg.msns.like;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.dxg.msns.like.mapper")
public class MSNSLikeServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MSNSLikeServiceApplication.class);
    }
}
