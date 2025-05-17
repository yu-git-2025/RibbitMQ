package com.yu.config;

import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * 生产者确认机制-配置类
 */
//@Configuration
public class MqConfirmConfig implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        RabbitTemplate rabbitTemplate = applicationContext.getBean(RabbitTemplate.class);
        //配置回调
        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
            @Override
            public void returnedMessage(ReturnedMessage returnedMessage) {
                System.out.println("收到消息的return callback - exchange : " + returnedMessage.getExchange()
                + " , routingkey : " + returnedMessage.getRoutingKey()
                + " , message: " + returnedMessage.getMessage()
                + " , code: " + returnedMessage.getReplyCode()
                + " , text: " + returnedMessage.getReplyText());
            }
        });
    }
}
