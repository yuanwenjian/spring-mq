package com.yuanwj.springmq.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
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
    public Queue queueTest(){
        return new Queue("test");
    }

    public Queue queueTest1(){
        return new Queue("yuanwj");
    }

    @Bean
    public Exchange exchange(){
        return new TopicExchange(configProperties.getTopicExchange());
    }

    @Bean
    public Binding binding(Queue queueTest,TopicExchange topicExchange){
        return BindingBuilder.bind(queueTest).to(topicExchange).with("*.orange.*");
    }

    @Bean
    public Binding bindingTest1(Queue queueTest1,TopicExchange topicExchange){
        return BindingBuilder.bind(queueTest1).to(topicExchange).with("*.*.rabbit");
    }

    @Bean
    public Binding bindingTest1_1(Queue queueTest1,TopicExchange topicExchange){
        return BindingBuilder.bind(queueTest1).to(topicExchange).with("lazy.#");
    }


    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory){
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        return rabbitAdmin;
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
