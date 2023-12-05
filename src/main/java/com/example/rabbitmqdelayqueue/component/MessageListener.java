package com.example.rabbitmqdelayqueue.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageListener {
    @RabbitListener(queues = "my-message-delay-queue")
    public void handleMessage(String message) {
        log.info("Received message: {}", message);
    }
}
