package com.jinaiya.tutorials.utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author Jin
 * @date 2019/1/9
 */
public class WxPushUtil {
    protected static final Logger logger = LoggerFactory.getLogger(WxPushUtil.class);

    private static final String URL = "https://pushbear.ftqq.com/sub?sendkey={sendkey}&text={text}&desp={desp}";

    private static final String NEWS_SENDKEY = "";

    private static final String DRINK_SENDKEY = "";

    public static void pushNews(String text) throws IOException {
        OkHttpClient client = new OkHttpClient();
        try {
            Request request = new Request
                    .Builder()
                    .url(URL
                            .replace("{sendkey}", NEWS_SENDKEY)
                            .replace("{text}", "头条资讯")
                            .replace("{desp}", text))
                    .build();
            Response response = client.newCall(request).execute();
            logger.info("response --->{}", response.body().string());
        } catch (Exception e) {
            logger.error("wx push news error ---> {}", e);
        }
    }

    public static void pushDrink() throws IOException {
        OkHttpClient client = new OkHttpClient();
        try {
            Request request = new Request
                    .Builder()
                    .url(URL
                            .replace("{sendkey}", DRINK_SENDKEY)
                            .replace("{text}", "大郎喝水啦づ￣3￣）づ╭❤～")
                            .replace("{desp}", "![img](https://s2.ax1x.com/2019/01/09/FL5Ohj.jpg)"))
                    .build();
            Response response = client.newCall(request).execute();
            logger.info("response --->{}", response.body().string());
        } catch (Exception e) {
            logger.error("wx push drink error ---> {}", e);
        }
    }
}
