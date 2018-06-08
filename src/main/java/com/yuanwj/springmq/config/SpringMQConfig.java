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

@Configuration
public class SpringMQConfig {

    private Logger LOG = LoggerFactory.getLogger(SpringMQConfig.class);

    private String topicExchange="yuanwj";

    private String queueName="test";

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
        template.setConfirmCallback(confirmCallback());
        template.setReturnCallback(returnCallback());
        return template;
    }
    @Bean
    public RabbitTemplate.ConfirmCallback confirmCallback(){
        RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String s) {
                if (ack){
                    LOG.debug("消息id为{}发送成功",correlationData);
                }else {
                    LOG.debug("消息id为{}发送失败,原因为{}",correlationData,s);
                }
            }
        };
        return confirmCallback;
    }

    public RabbitTemplate.ReturnCallback returnCallback(){
        RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int i, String s, String s1, String s2) {
                LOG.debug("==================发送失败");
                byte[] bytes = message.getBody();
                String aa = (String) SerializationUtils.deserialize(bytes);
            }
        };
        return returnCallback;
    }
}
