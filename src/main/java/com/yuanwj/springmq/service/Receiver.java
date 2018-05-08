package com.yuanwj.springmq.service;

import com.yuanwj.springmq.model.SendEntity;

public interface Receiver {

    public void receiver(SendEntity sendEntity);
}
