package com.hsb.rabbitmq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;
import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bin on 2014/12/18.
 */
public class QueueConsumer extends Base implements Runnable, Consumer {

    public QueueConsumer(String queueName) throws IOException {
        super(queueName);
    }

    @Override//Start a consumer
    public void handleConsumeOk(String consumerTag) {
        System.out.println("Consumer "+consumerTag +" registered");
    }

    @Override//cancel a consumer
    public void handleCancelOk(String s) {
        System.out.println("Consumer is canecled.");
    }

    @Override
    public void handleCancel(String s) throws IOException {

    }

    @Override
    public void handleShutdownSignal(String s, ShutdownSignalException e) {

    }

    @Override
    public void handleRecoverOk(String s) {

    }

    @Override
    public void handleDelivery(String s, Envelope envelope, AMQP.BasicProperties basicProperties, byte[] body) throws IOException {
        Map map = (HashMap) SerializationUtils.deserialize(body);
        System.out.println("Message Number "+ map.get("message number") + " received.");
    }

    @Override
    public void run() {
        try {
            channel.basicConsume(queueName, true, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
