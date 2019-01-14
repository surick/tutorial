package com.jinaiya.tutorials.handler.amqp;

import com.jinaiya.tutorials.config.Const;
import com.jinaiya.tutorials.model.News;
import com.jinaiya.tutorials.utils.NewsUtil;
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
        List<News> list = NewsUtil.getNews(type);
        list.stream()
                .forEach(item -> this.rabbitTemplate.convertAndSend(Const.DING_TALK_QUEUE,
                        "标题：" + item.getTitle()
                            + "\n作者：" + item.getAuthorName()
                            + "\n内容：" + item.getUrl()
                            + "\n日期：" + item.getDate()));
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            sb.append("<p><img src=" + list.get(i).getThumbnailPics()
                    + "></p><p>标题：" + list.get(i).getTitle()
                    + "</p><p>作者：" + list.get(i).getAuthorName()
                    + "</p><p>内容：" + list.get(i).getUrl()
                    + "</p><p>日期：" + list.get(i).getDate()
                    + "</p>------------------------------------------------------");
        }

        this.rabbitTemplate.convertAndSend(Const.EMAIL_QUEUE, sb.toString());

        StringBuilder wxNews = new StringBuilder();
        for (int i = 0; i < 15; i++) {
            wxNews.append("![img](" + list.get(i).getThumbnailPics() + ")"
                    + "\n\n [" + list.get(i).getTitle() + "](" + list.get(i).getUrl() + ")"
                    + "\n\n " + list.get(i).getAuthorName());
        }
//        list.stream()
//                .forEach(item -> wxNews.append("![img](" + item.getThumbnailPics() + ")"
//                        + "- " + item.getTitle()
//                        + "- " + item.getUrl()
//                        + "- " + item.getAuthorName()
//                        + "- " + item.getDate()
//                        + "---"
//                ));
//        this.rabbitTemplate.convertAndSend(Const.WX_QUEUE, wxNews.toString());

        logger.info("send news count-->{}", list.size());
    }
}
