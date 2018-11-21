package com.jinaiya.utils.handler;

import com.alibaba.fastjson.JSON;
import com.jinaiya.utils.model.DingTalkMsgTemplate;
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
        String webhook = "https://oapi.dingtalk.com/robot/send?access_token=49535a2ddc40daa276497b704a30da9e496d05e10155f6d0239b1e4fb7902ecb";
        if (StrUtil.isBlank(webhook)) {
            logger.error("dingding config error, webhook is blank-->{}", webhook);
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

