package com.jinaiya.tutorials.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jin
 * @date 2018/11/20
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue dingQueue() {
        return new Queue(Const.DING_TALK_QUEUE);
    }

    @Bean
    public Queue emailQueue() {
        return new Queue(Const.EMAIL_QUEUE);
    }

    @Bean
    public Queue wxQueue() {
        return new Queue(Const.WX_QUEUE);
    }
}
