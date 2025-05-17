package com.yu.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class DirectConfiguration {

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("test.direct");
    }

    @Bean
    public Queue directQueue() {
        //默认持久化 durable : true
        return new Queue("direct.queue");
    }

    @Bean
    public Binding directBinding1(Queue directQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(directQueue).to(directExchange).with("blue");
    }

    @Bean
    public Binding directBinding2(Queue directQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(directQueue).to(directExchange).with("red");
    }
}
