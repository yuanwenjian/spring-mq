package com.yuanwj.springmq.service.impl;

import com.yuanwj.springmq.config.MqConfigProperties;
import com.yuanwj.springmq.model.SendEntity;
import com.yuanwj.springmq.service.Sender;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestSender implements Sender{

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MqConfigProperties configProperties;

    @Override
    public void send(SendEntity sendEntity) {
//        byte[] bytes = SerializationUtils.serialize(sendEntity);
//        rabbitTemplate.convertAndSend(configProperties.getTopicExchange(),sendEntity.getRoutKey(),sendEntity,correlationId);
//        rabbitTemplate.convertAndSend("lazy.orange.fox","message为测试发送");
        rabbitTemplate.convertAndSend("yuanwj","lazy.orange.fox","message为测试发送");
    }
}
