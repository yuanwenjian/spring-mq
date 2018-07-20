package com.yuanwj.springmq.service.impl;

import com.rabbitmq.client.Channel;
import com.yuanwj.springmq.model.SendEntity;
import com.yuanwj.springmq.service.Receiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;

import java.io.IOException;

@Service
public class TestReceiver  {

    private Logger LOG = LoggerFactory.getLogger(TestReceiver.class);

//    @RabbitHandler
    @RabbitListener(queues = "yuanwj")
//    @Override
    public void receiver(Message message, Channel channel) throws Exception{
//        MessageListenerContainer
        System.out.println(Thread.currentThread().getName()+"=============");
        String key = message.getMessageProperties().getReceivedRoutingKey();
        LOG.debug("yuanwj消费成功,{},{}", key,"aaaaaaa");
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);

//        byte[] body = message.getBody();
//        SendEntity sendEntity = (SendEntity) SerializationUtils.deserialize(body);
//        try {
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
//            LOG.debug("yuanwj消费成功,{},{}", sendEntity.getRoutKey(), sendEntity.getSendName());
//        } catch (Exception e) {
//            LOG.debug("消费失败,routKey:{},name:{},deliveryTag:{}", sendEntity.getRoutKey(),
//                    sendEntity.getSendName(), message.getMessageProperties().getDeliveryTag());
//            try {
//                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
//        }
    }


    @RabbitListener(queues = "test")
    public void receiverTest(Message message, Channel channel) throws Exception{

        System.out.println(Thread.currentThread().getName()+"=============");
        String key = message.getMessageProperties().getReceivedRoutingKey();
        LOG.debug("yuanwj消费成功,{},{}", key,"bbbbbbbbbbbbb");
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);

//        String key = message.getMessageProperties().getReceivedRoutingKey();
//        byte[] body = message.getBody();
//        SendEntity sendEntity = (SendEntity) SerializationUtils.deserialize(body);
//        try {
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
//            LOG.debug("test消费成功,{},{}", sendEntity.getRoutKey(), sendEntity.getSendName());
//        } catch (Exception e) {
//            LOG.debug("消费失败,routKey:{},name:{},deliveryTag:{}", sendEntity.getRoutKey(),
//                    sendEntity.getSendName(), message.getMessageProperties().getDeliveryTag());
//            try {
//                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
//        }
    }
}
