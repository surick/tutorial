package com.jinaiya.tutorials.job;

import com.jinaiya.tutorials.handler.amqp.NewsSender;
import com.jinaiya.tutorials.handler.amqp.Sender;
import com.jinaiya.tutorials.utils.WxPushUtil;
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

    // 9-17每小时推送
    @Scheduled(cron = "0 0/59 9-17 * * ?")
    public void cronSender() throws IOException{
        WxPushUtil.pushDrink();
    }

    // 每天早八点 晚五点推送新闻
    @Scheduled(cron = "0 0 8,17 * * ? ")
    public void cronNews() throws IOException {
        newsSender.sendNews("top");
    }
}
