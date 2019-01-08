package com.jinaiya.tutorials.job;

import com.jinaiya.tutorials.handler.amqp.NewsSender;
import com.jinaiya.tutorials.handler.amqp.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Jin
 * @date 2018/11/21
 */
@Component
public class CronTasks {
    protected static final Logger logger = LoggerFactory.getLogger(CronTasks.class);

    @Autowired
    private Sender sender;
    @Autowired
    private NewsSender newsSender;

//    @Scheduled(cron = "0/30 * * * * ?")
    public void cronSender() {
        sender.send();
        logger.info("ok!");
    }

    // 每天早八点推送新闻
    @Scheduled(cron = "0 0 8 * * ?")
    public void cronNews() throws IOException {
        newsSender.sendNews("top");
    }
}
