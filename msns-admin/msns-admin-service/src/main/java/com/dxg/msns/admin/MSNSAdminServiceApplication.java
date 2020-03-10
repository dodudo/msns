package com.dxg.msns.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.dxg.msns.admin.mapper")
public class MSNSAdminServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MSNSAdminServiceApplication.class);
    }
}
