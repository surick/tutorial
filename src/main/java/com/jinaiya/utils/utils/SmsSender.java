package com.jinaiya.utils.utils;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author Jin
 * @date 2018/11/24
 */
public class SmsSender {
    protected static final Logger logger = LoggerFactory.getLogger(SmsSender.class);

    public static boolean smsSingleSender(String phone, String text) {
        Boolean flag = false;
        try {
            SmsSingleSender ssender = new SmsSingleSender(1400162442,
                    "1cc8d2e052bc03f299ff566be6d91849");
            SmsSingleSenderResult result = ssender.send(0, "86", phone,
                    text, "", "");
            flag = true;
            logger.info("sms send---{}", result);
        } catch (HTTPException e) {
            // http响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络io错误
            e.printStackTrace();
        }
        return flag;
    }

    public static void main(String[] args) {
        smsSingleSender("13013623154", "sms api test");
    }
}
