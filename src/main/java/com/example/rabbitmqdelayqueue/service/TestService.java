package com.example.rabbitmqdelayqueue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    private final RabbitService rabbitService;

    @Autowired
    public TestService(RabbitService rabbitSender) {
        this.rabbitService = rabbitSender;
    }

    public void sendDelayedMessage(long second) {
        rabbitService.delayedSend("my-exchange", "my-routingkey", "Hello, RabbitMQ!", second * 1000L);
    }
}
