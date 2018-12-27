package com.jinaiya.tutorials.handler.amqp;

import com.jinaiya.tutorials.config.Const;
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
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "hello surick, now is " + new Date();
        System.out.println("Sender: " + context);
        this.rabbitTemplate.convertAndSend(Const.DING_TALK_QUEUE, context);
    }

}
