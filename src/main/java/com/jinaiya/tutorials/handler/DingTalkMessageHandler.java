package com.jinaiya.tutorials.handler;

import com.alibaba.fastjson.JSON;
import com.jinaiya.tutorials.config.Const;
import com.jinaiya.tutorials.model.DingTalkMsgTemplate;
import com.xiaoleilu.hutool.http.HttpUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 钉钉机器人
 * @author Jin
 * @date 2018/11/21
 */
@Component
public class DingTalkMessageHandler {
    protected static final Logger logger = LoggerFactory.getLogger(DingTalkMessageHandler.class);

    public boolean process(String text) {
        String webhook = Const.DING_TALK_WEBHOOK;
        if (StrUtil.isBlank(webhook)) {
            logger.error("ding config error, webhook is blank-->{}", webhook);
            return false;
        }

        DingTalkMsgTemplate msg = new DingTalkMsgTemplate();
        msg.setMsgtype("text");
        DingTalkMsgTemplate.TextBean textBean = new DingTalkMsgTemplate.TextBean();
        textBean.setContent(text);
        msg.setText(textBean);
        String result = HttpUtil.post(webhook, JSON.toJSONString(msg));
        logger.info("msg send ok, response ---> {}", result);
        return true;
    }
}

