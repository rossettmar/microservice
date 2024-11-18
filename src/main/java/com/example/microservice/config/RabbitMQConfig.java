package com.example.microservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.name}")
    private String queue;

    @Value("${rabbitmq.topic.exchange}")
    private String topic;

    @Value("${rabbitmq.routing.key}")
    private String routing_key;

    // bean che definisce la coda
    @Bean
    public Queue queue(){
        return new Queue(queue);
    }

    // bean che definisce l'exchange
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(topic);
    }

    // effettua il binding coda -> exchange
    @Bean
    public Binding binding(){
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(routing_key);
    }

    /* Con springboot non c'è bisogno di configurare questi tre beans

     - ConnectionFactory
     - RabbitTemplate
     - RabbitAdmin

    */
}
