package com.jinaiya.utils.amqp;

import com.jinaiya.utils.handler.DingTalkMessageHandler;
import com.jinaiya.utils.model.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jin
 * @date 2018/11/20
 */
@Component
@RabbitListener(queues = Const.DING_TALK_QUEUE)
public class Receiver {
    protected static final Logger logger = LoggerFactory.getLogger(Receiver.class);

    @Autowired
    private DingTalkMessageHandler dingTalkMessageHandler;

    @RabbitHandler
    public void process(String hello) {
        long startTime = System.currentTimeMillis();
        logger.info("msg bus receive --> {}", hello);
        dingTalkMessageHandler.process(hello);
        long useTime = System.currentTimeMillis() - startTime;
        logger.info("process ok, cost ---> {}", useTime);
    }
}
