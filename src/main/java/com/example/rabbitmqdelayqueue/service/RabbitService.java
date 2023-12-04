package com.example.rabbitmqdelayqueue.service;

import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitService {
    private final RabbitTemplate rabbitTemplate;

    public RabbitService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void delayedSend(String exchange, String routingKey, Object messageContent, final long delayTimes) {
        rabbitTemplate.convertAndSend(exchange,
                routingKey,
                messageContent,
                message -> {
                    // message 세팅
                    message.getMessageProperties().setHeader("x-delay", delayTimes);
                    message.getMessageProperties().setExpiration("10000");
                    message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                    return message;
                });
    }
}
