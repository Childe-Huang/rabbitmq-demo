package com.hsb.rabbitmq;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Bin on 2014/12/18.
 */
public class Test {

    @org.junit.Test
    public void testRabbitMQ() throws Exception{

        int flag;
        //启动consumer线程
        QueueConsumer consumer = new QueueConsumer("queue");
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();


        Producer producer = new Producer("queue");
        for (int i = 0; i <= 100000; i++) {
            HashMap message = new HashMap();
            message.put("message number", i);
            producer.sendMessage(message);
            System.out.println("Message Number " + i + "has been sent.");
        }
        Thread.sleep(20000);
    }

}
