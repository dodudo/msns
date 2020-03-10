package com.dxg.msns.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.dxg.msns.user.mapper")
public class MSNSUserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MSNSUserServiceApplication.class);
    }
}
