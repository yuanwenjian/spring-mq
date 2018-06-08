package com.yuanwj.springmq.service.impl;

import com.rabbitmq.client.Channel;
import com.yuanwj.springmq.model.SendEntity;
import com.yuanwj.springmq.service.Receiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;

@Service
public class TestReceiver implements Receiver {

    private Logger LOG = LoggerFactory.getLogger(TestReceiver.class);
    @RabbitHandler
    @RabbitListener(queues = "test")
    @Override
    public void receiver(Message message, Channel channel) {
        String key = message.getMessageProperties().getReceivedRoutingKey();
        byte[] body = message.getBody();
        SendEntity sendEntity = (SendEntity) SerializationUtils.deserialize(body);
        LOG.debug("消费成功,{},{}",sendEntity.getRoutKey(),sendEntity.getSendName());
//        System.out.println("==========="+sendEntity.getRoutKey());
    }
}
