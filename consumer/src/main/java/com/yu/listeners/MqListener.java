package com.yu.listeners;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MqListener {

    @RabbitListener(queues = "test.queue")
    public void listenQueue(Map<String,Object> msg) {
        System.out.println("test.queue : "+msg);
    }

    /**
     * fanout交换机-队列-创建-并绑定
     * @param msg
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "fanout.queue1",durable = "true"),
            exchange = @Exchange(name = "test.fanout",type = ExchangeTypes.FANOUT)
    ))
    public void fanoutListenerQueue1(String msg) {
        System.out.println("fanout-queue1 : "+msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "fanout.queue2",durable = "true"),
            exchange = @Exchange(name = "test.fanout",type = ExchangeTypes.FANOUT)
    ))
    public void fanoutListenerQueue2(String msg) {
        System.out.println("fanout-queue2 : "+msg);
    }


    /**
     * direct交换机-队列-创建-绑定
     * @param msg
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue1",durable = "true"),
            exchange = @Exchange(name = "test.direct",type = ExchangeTypes.DIRECT),
            key={"blue","red"}
    ))
    public void listenDirectQueue1(String msg) {
        System.out.println("direct-queue1 : "+msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue2",durable = "true"),
            exchange = @Exchange(name = "test.direct",type = ExchangeTypes.DIRECT),
            key={"blue","green"}
    ))
    public void listenDirectQueue2(String msg) {
        System.out.println("direct-queue2 : "+msg);
    }


    /**
     * topic交换机-队列-创建-绑定
     * @param msg
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue1",durable = "true"),
            exchange = @Exchange(name = "test.topic",type = ExchangeTypes.TOPIC),
            key = "xian.#"
    ))
    public void listenTopicQueue1(String msg) {
        System.out.println("topic-queue1 : "+msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue2",durable = "true"),
            exchange = @Exchange(name = "test.topic",type = ExchangeTypes.TOPIC),
            key = "#.weather"
    ))
    public void listenTopicQueue2(String msg) {
        System.out.println("topic-queue2 : "+msg);
    }

}
