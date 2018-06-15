package com.yuanwj.springmq.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.SerializationUtils;

import javax.annotation.Resource;

@Configuration
public class SpringMQConfig {

    private Logger LOG = LoggerFactory.getLogger(SpringMQConfig.class);

    private String topicExchange="yuanwj";

    private String queueName="test";

    @Resource
    private MqReturnCallback returnCallback;

    @Resource
    private MqConfirmCallback confirmCallback;

    @Bean
    public Queue queue(){
        return new Queue(queueName);
    }

    @Bean
    public Exchange exchange(){
        return new TopicExchange(topicExchange);
    }

    @Bean
    public Binding binding(Queue queue,TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("yuanwj.#");
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setConfirmCallback(confirmCallback);
        template.setReturnCallback(returnCallback);
        template.setMandatory(true);
        return template;
    }
}
