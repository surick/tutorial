package com.jinaiya.tutorials.handler.amqp;

import com.jinaiya.tutorials.config.Const;
import com.jinaiya.tutorials.model.News;
import com.jinaiya.tutorials.utils.NewsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * @author Jin
 * @date 2019/1/8
 */
@Component
public class NewsSender {

    protected static final Logger logger = LoggerFactory.getLogger(NewsSender.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void sendNews(String type) throws IOException {
        List<News> list = NewsUtils.getNews(type);
        list.stream()
                .forEach(item -> this.rabbitTemplate.convertAndSend(Const.DING_TALK_QUEUE,
                        "标题：" + item.getTitle()
                            + "\n作者：" + item.getAuthorName()
                            + "\n内容：" + item.getUrl()
                            + "\n日期：" + item.getDate()));
        logger.info("send news count-->{}", list.size());
    }
}