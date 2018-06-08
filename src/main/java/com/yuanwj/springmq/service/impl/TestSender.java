package com.yuanwj.springmq.service.impl;

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

    @Override
    public void send(SendEntity sendEntity) {
//        byte[] bytes = SerializationUtils.serialize(sendEntity);
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());

        rabbitTemplate.convertAndSend("yuanwj",sendEntity.getRoutKey(),sendEntity,correlationId);
        String a=null;
        System.out.println(a.length());
        System.out.println("=============");
    }
}
