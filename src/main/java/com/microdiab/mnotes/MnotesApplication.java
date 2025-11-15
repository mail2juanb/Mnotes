package com.microdiab.mnotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MnotesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MnotesApplication.class, args);
    }

}
