package com.example.microservice.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQPRoducer {

    @Value("${rabbitmq.topic.exchange}")
    private String exchange;
    @Value("${rabbitmq.routing.key}")
    private String routing_key;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQPRoducer.class);

    private final RabbitTemplate rabbitTemplate;

    // constructor dependency injection del RabbitTemplate
    public RabbitMQPRoducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message){
        LOGGER.info(String.format("Message sent -> %s", message ));
        rabbitTemplate.convertAndSend(exchange, routing_key, message);
    }

}
