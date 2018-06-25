package com.yuanwj.springmq.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class SpringMQConfig {

    private Logger LOG = LoggerFactory.getLogger(SpringMQConfig.class);

    @Autowired
    private MqConfigProperties configProperties;

    @Resource
    private MqReturnCallback returnCallback;

    @Resource
    private MqConfirmCallback confirmCallback;

    @Bean
    public Queue queue(){
        return new Queue(configProperties.getQueueName());
    }

    @Bean
    public Exchange exchange(){
        return new TopicExchange(configProperties.getTopicExchange());
    }

    @Bean
    public Binding binding(Queue queue,TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(configProperties.getRoutingKey());
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
