package com.example.rabbitmqdelayqueue;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RabbitmqDelayQueueApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqDelayQueueApplication.class, args);
    }


    @Bean
    public Queue myQueue() {
        return new Queue("my-queue", true);
    }
}
