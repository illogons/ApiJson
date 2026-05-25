package org.example.apijson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ApiJsonApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiJsonApplication.class, args);
    }

}
