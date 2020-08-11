package com.leftorright.rsscreator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.leftorright.rsscreator.dao"})
public class RsscreatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(RsscreatorApplication.class, args);
    }

}
