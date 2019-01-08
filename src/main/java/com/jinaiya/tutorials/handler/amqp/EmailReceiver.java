package com.jinaiya.tutorials.handler.amqp;

import com.jinaiya.tutorials.config.Const;
import com.jinaiya.tutorials.handler.EmailHandler;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jin
 * @date 2019/1/8
 */
@Component
public class EmailReceiver {
    @Autowired
    private EmailHandler emailHandler;

    @RabbitListener(queues = Const.EMAIL_QUEUE)
    @RabbitHandler
    public void process(String full) {
        emailHandler.process(full);
    }
}
