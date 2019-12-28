package com.hraf.kafkaspringintro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class KafkaSpringIntroApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaSpringIntroApplication.class, args);
    }

}
