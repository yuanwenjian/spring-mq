package com.yuanwj.springmq.rest;

import com.yuanwj.springmq.model.TestEntity;
import com.yuanwj.springmq.service.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/rabbit/")
public class TestResource {

    @Autowired
    private Sender sender;

    @RequestMapping(value = "/send",method = RequestMethod.GET)
    public String send(String message){
        TestEntity entity = new TestEntity();
        entity.setSendName("test");
        entity.setRoutKey("yuanwj.test");
        entity.setMessage(message);
        sender.send(entity);
        return message;
    }
}
