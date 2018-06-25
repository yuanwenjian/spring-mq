package com.yuanwj.springmq.service.impl;

import com.yuanwj.springmq.config.MqConfigProperties;
import com.yuanwj.springmq.model.SendEntity;
import com.yuanwj.springmq.service.Sender;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TestSender implements Sender{

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MqConfigProperties configProperties;

    @Override
    public void send(SendEntity sendEntity) {
//        byte[] bytes = SerializationUtils.serialize(sendEntity);
        for (int i=0;i<100;i++){
            CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
            System.out.println(Thread.currentThread().getName()+"=============");
            rabbitTemplate.convertAndSend(configProperties.getTopicExchange(),sendEntity.getRoutKey(),sendEntity,correlationId);
        }
    }
}
