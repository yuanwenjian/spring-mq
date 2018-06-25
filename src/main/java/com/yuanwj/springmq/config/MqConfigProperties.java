package com.yuanwj.springmq.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "mq")
public class MqConfigProperties {

    private  String topicExchange;

    private  String queueName;

    private String routingKey;

    public  String getTopicExchange() {
        return topicExchange;
    }

    public  String getQueueName() {
        return queueName;
    }

    public void setTopicExchange(String topicExchange) {
        this.topicExchange = topicExchange;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }
}
