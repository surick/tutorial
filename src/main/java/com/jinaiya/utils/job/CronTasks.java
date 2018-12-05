package com.jinaiya.utils.job;

import com.jinaiya.utils.amqp.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Jin
 * @date 2018/11/21
 */
@Component
public class CronTasks {
    protected static final Logger logger = LoggerFactory.getLogger(CronTasks.class);

    @Autowired
    private Sender sender;

    @Scheduled(cron = "0/30 * * * * ?")
    public void cronSender() {
        sender.send();
        logger.info("ok!");
    }
}