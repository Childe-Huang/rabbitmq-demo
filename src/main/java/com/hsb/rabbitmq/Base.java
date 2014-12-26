package com.hsb.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * Created by Bin on 2014/12/18.
 */
public abstract class Base {
    protected Channel channel;
    protected Connection connection;
    protected String queueName;

    public Base(String queueName) throws IOException {
        this.queueName = queueName;
        //创建ConnectionFactory
        ConnectionFactory factory = new ConnectionFactory();
        //rabbitmq服务器的主机名
        factory.setHost("192.168.159.128");
        factory.setUsername("childe128");
        factory.setPassword("childe128");
        factory.setVirtualHost("/");
        //获取connection
        connection = factory.newConnection();
        //创建channel
        channel = connection.createChannel();
        //为channel声明一个queue，若queue不存在则新建该队列
        channel.queueDeclare(queueName, false, false, false, null);
    }

    /**
     * 关闭channel和connection
     * @throws IOException
     */
    public void close() throws IOException {
        this.channel.close();
        this.connection.close();
    }
}
