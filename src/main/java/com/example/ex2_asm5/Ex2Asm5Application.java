package com.example.ex2_asm5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"com.example.ex2_asm5.entity"})
public class Ex2Asm5Application {

    public static void main(String[] args) {
        SpringApplication.run(Ex2Asm5Application.class, args);
    }

}
