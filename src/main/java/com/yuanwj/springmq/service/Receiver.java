package com.yuanwj.springmq.service;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;

public interface Receiver {

    public void receiver(Message message, Channel channel) throws Exception;
}
