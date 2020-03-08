package com.dxg.msns.music;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.dxg.msns.music.mapper")
public class MSNSMusicServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MSNSMusicServiceApplication.class);
    }
}
