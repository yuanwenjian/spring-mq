package com.yuanwj.springmq.config;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
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
    public Queue queueTest() {
        return new Queue("test");
    }

    @Bean
    public Queue queueTest1() {
        return new Queue("yuanwj");
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(configProperties.getTopicExchange());
    }

    @Bean
    public Binding binding() {
        return new Binding("test", Binding.DestinationType.QUEUE, configProperties.getTopicExchange(), "*.orange.*", null);
//        return BindingBuilder.bind(queueTest).to(topicExchange).with("*.orange.*");
    }

    @Bean
    public Binding bindingList() {
        return new Binding("yuanwj", Binding.DestinationType.QUEUE, configProperties.getTopicExchange(), "*.*.rabbit", null);
    }

    @Bean
    public Binding bindTest(){
        return new Binding("yuanwj", Binding.DestinationType.QUEUE, configProperties.getTopicExchange(), "lazy.#", null);
    }
//    @Bean
//    public Binding bindingTest1(Queue queueTest1){
//        return BindingBuilder.bind(queueTest1()).to(exchange()).with("*.*.rabbit");
//    }
//
//    @Bean
//    public Binding bindingTest1_1(Queue queueTest1,TopicExchange topicExchange){
//        return BindingBuilder.bind(queueTest1()).to(exchange()).with("lazy.#");
//    }

    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setConfirmCallback(confirmCallback);
        template.setReturnCallback(returnCallback);
        template.setMandatory(true);
        return template;
    }



}
