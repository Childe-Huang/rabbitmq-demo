package com.hsb.rabbitmq;

import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Bin on 2014/12/18.
 */
public class Producer extends Base {

    public Producer(String queueName) throws IOException {
        super(queueName);
    }

    /**
     * 消息发送
     * @param object
     * @throws IOException
     */
    public void sendMessage(Serializable object) throws IOException {
        channel.basicPublish("", queueName, null, SerializationUtils.serialize(object));
    }
}
