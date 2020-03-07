package com.dxg.msns.dynamic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.dxg.msns.dynamic.mapper")
public class MSNSDynamicServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MSNSDynamicServiceApplication.class);
    }
}
