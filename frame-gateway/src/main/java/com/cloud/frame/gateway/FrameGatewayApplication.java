package com.cloud.frame.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableEurekaClient
@EnableWebFlux
@EnableFeignClients(basePackages = {"com.cloud.frame"})
@ComponentScan("com.cloud.frame")
public class FrameGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrameGatewayApplication.class, args);
    }

}
