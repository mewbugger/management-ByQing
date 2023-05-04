package com.managementbyqing.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.managementbyqing.boot.mapper")
public class BootApplication {

    public static void main(String[] args) {
        //SpringApplication.run(BootApplication.class, args);
        SpringApplication springApplication = new SpringApplication(BootApplication.class);
        springApplication.setAllowCircularReferences(Boolean.TRUE);
        springApplication.run(args);

    }

}
