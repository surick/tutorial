package com.jinaiya.utils.amqp;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Jin
 * @date 2018/11/20
 */
@Component
public class Sender {
    @Autowired
    private AmqpTemplate rabbitTemplcate;

    public void send() {
        String context = "hello " + new Date();
        System.out.println("Sender: " + context);
        this.rabbitTemplcate.convertAndSend("hello", context);
    }

}
