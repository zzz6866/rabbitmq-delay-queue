package com.example.rabbitmqdelayqueue;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class RabbitmqDelayQueueApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqDelayQueueApplication.class, args);
    }


    /**
     * Creates a delayed exchange with the name "message-delay-exchange" and type "x-delayed-message".
     *
     * @return the created CustomExchange object
     */
    @Bean
    public CustomExchange delayedExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange("my-exchange", "x-delayed-message", true, false, args);
    }

    /**
     * Creates and returns a new instance of the Binding class with the given parameters.
     * The Queue object provided is bound to the CustomExchange object using the provided routing
     * key "delay-routingkey" and no additional arguments.
     *
     * @param messageDelayQueue the Queue object to be bound
     * @param delayedExchange   the CustomExchange object to bind the Queue to
     * @return the newly created Binding object
     */
    @Bean
    public Binding binding(Queue messageDelayQueue, CustomExchange delayedExchange) {
        return BindingBuilder.bind(messageDelayQueue).to(delayedExchange).with("my-routingkey").noargs();
    }


    /**
     * Creates and returns a new instance of the Queue class with the specified name.
     * The created Queue object represents a message delay queue.
     *
     * @return the newly created Queue object
     */
    @Bean
    public Queue myMessageDelayQueue() {
        return new Queue("my-message-delay-queue", true);
    }
}
