package com.vh1ne.ec2filetest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Ec2FileTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ec2FileTestApplication.class, args);
    }

}
