package com.yuanwj.springmq.model;

import java.io.Serializable;

public  class SendEntity implements Serializable {

    private String routKey;

    private String sendName;

    public String getRoutKey() {
        return routKey;
    }

    public void setRoutKey(String routKey) {
        this.routKey = routKey;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }
}
