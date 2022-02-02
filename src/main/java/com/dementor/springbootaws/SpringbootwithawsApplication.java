package com.dementor.springbootaws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringbootwithawsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootwithawsApplication.class, args);
    }

}
