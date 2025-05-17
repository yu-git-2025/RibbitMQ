package com.yu;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class PublisherApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void testSendMessageQueue() {
        String queueName = "test.queue";
        Map<String, Object> msg = new HashMap<>(4);
        msg.put("name","tom");
        msg.put("age",18);
        rabbitTemplate.convertAndSend(queueName,msg);
    }


    /**
     * fanout
     */
    @Test
    void testSendMessageFanout() {
        String exchangeName = "test.fanout";
        String msg = "Hello Fanout";
        rabbitTemplate.convertAndSend(exchangeName,null, msg);
    }

    /**
     * direct
     */
    @Test
    void testSendMessageDirect1() {
        String exchangeName = "test.direct";
        String msg = "Hello Direct";
        rabbitTemplate.convertAndSend(exchangeName,"blue", msg);
    }

    @Test
    void testSendMessageDirect2() {
        String exchangeName = "test.direct";
        String msg = "Hello Direct";
        rabbitTemplate.convertAndSend(exchangeName,"red", msg);
    }

    /**
     * topic
     */
    @Test
    void testSendMessagetopic1() {
        String exchangeName = "test.topic";
        String msg = "Hello Topic";
        rabbitTemplate.convertAndSend(exchangeName,"beijin.weather", msg);
    }

    @Test
    void testSendMessagetopic2() {
        String exchangeName = "test.topic";
        String msg = "Hello Topic";
        rabbitTemplate.convertAndSend(exchangeName,"xian.weather",msg);
    }

}
