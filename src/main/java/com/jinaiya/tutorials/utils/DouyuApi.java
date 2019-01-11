package com.jinaiya.tutorials.utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author Jin
 * @date 2019/1/10
 */
public class DouyuApi {
    protected static final Logger logger = LoggerFactory.getLogger(DouyuApi.class);

    private static final String TARGET_URL = "https://www.douyu.com/{room}";

    public static String isOnline(String room) throws IOException {
        OkHttpClient client = new OkHttpClient();
        try {
            Request request = new Request
                    .Builder()
                    .url(TARGET_URL.replace("{room}", room))
                    .build();
            Response response = client.newCall(request).execute();

            if (response.body().string().contains("show_status = 1")) {
                return "房间号：" + room + " 正在直播，点击 " + TARGET_URL.replace("{room}", room) + " 访问！";
            }

            return "房间号：" + room + " 没有直播！";
        } catch (Exception e) {
            logger.error("douyu api error --> {}", e);
            return "网络异常";
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println(isOnline("9999"));
    }
}
