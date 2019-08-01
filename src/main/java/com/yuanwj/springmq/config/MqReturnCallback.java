package com.yuanwj.springmq.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
/**
 * 由exchange发送至队列时确认，
 */
public class MqReturnCallback implements RabbitTemplate.ReturnCallback {
    private static final Logger LOG = LoggerFactory.getLogger(MqReturnCallback.class);

    private RabbitTemplate rabbitTemplate;

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {

        LOG.info("消息发送失败,replyCode:{},replyText:{},exchange:{},routingKey:{}",
                replyCode, replyText,exchange,routingKey);
    }
}
