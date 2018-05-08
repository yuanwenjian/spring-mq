package com.yuanwj.springmq.service.impl;

import com.yuanwj.springmq.model.SendEntity;
import com.yuanwj.springmq.service.Sender;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestSender implements Sender{

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void send(SendEntity sendEntity) {
        rabbitTemplate.convertAndSend("yuanwj",sendEntity.getRoutKey(),sendEntity);
    }
}
