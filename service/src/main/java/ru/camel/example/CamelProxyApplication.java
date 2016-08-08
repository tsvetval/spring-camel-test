package ru.camel.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * see http://camel.apache.org/spring-boot.html
 */
@SpringBootApplication
@EnableAutoConfiguration
public class CamelProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(CamelProxyApplication.class, args);
    }

}
