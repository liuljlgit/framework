package com.cloud.frame.frameauth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.cloud.frame"})
@ComponentScan("com.cloud")
@MapperScan("com.cloud.**.dao")
public class FrameAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrameAuthApplication.class, args);
    }

}
