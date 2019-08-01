# Rabbit-mq笔记

## 消费者确认机制

### 自动确认
这种情况下，发后即丢，有可能丢失数据

### 手动确认
这种模式需要手动确认，由于网络或者设备等问题导致消息没有被确认，需要手动处理，可能会产生相同的数据

channel.basicAck 确认收到消息并确认

channel.basicReject 消费者没有处理消息

channel.basicNack basicReject的拓展，拒绝多条消息

### 注意
如果channel使用手动确认，但是由于各种原因导致没有被确认，会导致服务端不会再发送数据给消费者，因为认为该消费者处理能力有限


## 生产者确认

生产者分确认分为两种情况，一种为消息发送exchange确认，另一部为exchange将消息发送至队列

1. 消息发送至exchange确认 使用 RabbitTemplate.ReturnCallback confirm()方法

2. exchange将消息发送至队列，使用RabbitTemplate.ReturnCallback returnedMessage()方法