package com.yuanwj.springmq.service;

import com.yuanwj.springmq.model.SendEntity;

public interface Sender {

    void send(SendEntity sendEntity);
}
