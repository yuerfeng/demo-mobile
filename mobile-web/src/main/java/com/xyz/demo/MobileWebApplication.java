package com.xyz.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.xyz.demo"})
@MapperScan(basePackages = {"com.xyz.demo.dao"})
public class MobileWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MobileWebApplication.class, args);
    }

}
