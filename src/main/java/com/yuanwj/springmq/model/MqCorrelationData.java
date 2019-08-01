package com.yuanwj.springmq.model;


import org.springframework.amqp.rabbit.connection.CorrelationData;

public class MqCorrelationData extends CorrelationData {
    private boolean isReturn;

    public boolean isReturn() {
        return isReturn;
    }

    public void setReturn(boolean aReturn) {
        isReturn = aReturn;
    }
}
