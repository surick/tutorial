package com.jinaiya.utils.config;

import com.jinaiya.utils.model.Const;
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
    public Queue helloQueue() {
        return new Queue(Const.DING_TALK_QUEUE);
    }
}
