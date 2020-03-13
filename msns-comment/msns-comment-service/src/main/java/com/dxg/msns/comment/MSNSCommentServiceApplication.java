package com.dxg.msns.comment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.dxg.msns.comment.mapper")
public class MSNSCommentServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MSNSCommentServiceApplication.class);
    }
}
