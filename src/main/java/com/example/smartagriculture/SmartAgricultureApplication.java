package com.example.smartagriculture;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.example.smartagriculture.mapper")
@EnableScheduling
public class SmartAgricultureApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartAgricultureApplication.class, args);
    }

}
