package com.yu.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息失败处理
 */
@Configuration
public class ErrorConfiguration {

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("error.direct");
    }

    @Bean
    public Queue errorQueue() {
        return new Queue("error.queue");
    }

    @Bean
    public Binding errorBinding(DirectExchange directExchange, Queue errorQueue) {
        return BindingBuilder.bind(errorQueue).to(directExchange).with("error");
    }

    @Bean
    public MessageRecoverer messageRecoverter(RabbitTemplate rabbitTemplate) {
        return  new RepublishMessageRecoverer(rabbitTemplate,"error.direct","error");
    }
}
