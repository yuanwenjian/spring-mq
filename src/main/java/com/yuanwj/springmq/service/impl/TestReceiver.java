package com.yuanwj.springmq.service.impl;

import com.yuanwj.springmq.model.SendEntity;
import com.yuanwj.springmq.service.Receiver;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@RabbitListener(queues = "test")
@Service
public class TestReceiver implements Receiver {

    @RabbitHandler
    @Override
    public void receiver(SendEntity sendEntity) {
        System.out.println("==========="+sendEntity.getRoutKey());
    }
}
