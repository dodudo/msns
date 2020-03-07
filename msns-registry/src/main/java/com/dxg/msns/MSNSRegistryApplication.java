package com.dxg.msns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MSNSRegistryApplication {
    public static void main(String[] args) {
        SpringApplication.run(MSNSRegistryApplication.class,args);
    }
}
