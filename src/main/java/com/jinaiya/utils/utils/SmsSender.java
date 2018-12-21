package com.jinaiya.utils.utils;

import com.github.qcloudsms.SmsMultiSender;
import com.github.qcloudsms.SmsMultiSenderResult;
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

    // 短信应用SDK AppID
    public static final int APPID = 140; // 1400开头

    // 短信应用SDK AppKey
    public static final String APPKEY = "";

    // 短信模板ID，需要在短信应用中申请
    public static final int TEMPLATEID = 1;

    // 签名
    public static final String SMSSIGN = "";

    public static boolean smsSingleSender(String phone, String[] text) {
        Boolean flag = false;
        try {
            SmsSingleSender ssender = new SmsSingleSender(APPID, APPKEY);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phone,
                    TEMPLATEID, text, SMSSIGN, "", "");
            flag = true;
            logger.info("sms send--->{}", result);
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

    public static boolean smsMultiSender(String[] phoneNumbers, String[] text) {
        Boolean flag = false;
        try {
            SmsMultiSender msender = new SmsMultiSender(APPID, APPKEY);
            SmsMultiSenderResult result =  msender.sendWithParam("86", phoneNumbers,
                    TEMPLATEID, text, SMSSIGN, "", "");
            System.out.println(result);
            flag = true;
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }
        return flag;
    }

    public static void main(String[] args) {
        smsSingleSender("1762565559", new String[]{"sbsbsb", "sxsxsx"});
        smsMultiSender(new String[]{"1301362315", "1760521676", "1755100921", "1762565559"}, new String[]{"你是小狗", "你是小🐶"});
    }
}
