package com.jinaiya.tutorials.handler.amqp;

import com.jinaiya.tutorials.handler.DingTalkMessageHandler;
import com.jinaiya.tutorials.config.Const;
import com.jinaiya.tutorials.utils.AliEmailUtil;
import com.jinaiya.tutorials.utils.WxPushUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Jin
 * @date 2018/11/20
 */
@Component
public class Receiver {
    protected static final Logger logger = LoggerFactory.getLogger(Receiver.class);

    @Autowired
    private DingTalkMessageHandler dingTalkMessageHandler;

    @RabbitListener(queues = Const.DING_TALK_QUEUE)
    @RabbitHandler
    public void process(String content) {
        long startTime = System.currentTimeMillis();
        logger.info("msg bus receive --> {}", content);

        dingTalkMessageHandler.process(content);

        long useTime = System.currentTimeMillis() - startTime;
        logger.info("process ok, cost ---> {}", useTime);
    }

    @RabbitListener(queues = Const.WX_QUEUE)
    @RabbitHandler
    public void processWx(String content) throws IOException {
        WxPushUtil.pushNews(content);
    }

    @RabbitListener(queues = Const.EMAIL_QUEUE)
    @RabbitHandler
    public void processEmail(String content) {
        AliEmailUtil.sendEmail(content);
    }
}
