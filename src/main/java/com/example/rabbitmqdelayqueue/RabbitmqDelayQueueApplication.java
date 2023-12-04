package com.example.rabbitmqdelayqueue;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
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
     * Creates and returns a new instance of the Queue class named "my-queue" with durable set to true.
     *
     * @return the newly created Queue object
     */
    @Bean
    public Queue myQueue() {
        return new Queue("my-queue", true);
    }

    /**
     * Creates and returns a new instance of the DirectExchange class named "my-exchange" with durable set to true,
     * internal set to false, and arguments set to the supplied map of arguments.
     * The argument "x-delayed-type" is set to "direct" in the map as a placeholder.
     *
     * @return the newly created DirectExchange object
     */
    @Bean
    public DirectExchange delayedExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new DirectExchange("my-exchange", true, false, args);
    }

    /**
     * Creates and returns a new instance of the Binding class that binds the supplied queue to the supplied exchange
     * using the specified routing key. The created Binding object represents a binding between the myQueue and delayedExchange.
     *
     * @param myQueue the Queue object that will be bound to the exchange
     * @param delayedExchange the DirectExchange object to which the queue will be bound
     * @return the newly created Binding object
     */
    @Bean
    public Binding binding(Queue myQueue, DirectExchange delayedExchange) {
        return BindingBuilder.bind(myQueue).to(delayedExchange).with("my-routingkey");
    }
}
