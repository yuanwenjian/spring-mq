package com.yuanwj.springmq.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConfirmCallback implements RabbitTemplate.ConfirmCallback {
    private static final Logger LOG = LoggerFactory.getLogger(MqConfirmCallback.class);
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String s) {
        if (ack){
            LOG.debug("消息id为{}发送成功",correlationData);
        }else {
            LOG.debug("消息id为{}发送失败,原因为{}",correlationData,s);
        }
    }
}
