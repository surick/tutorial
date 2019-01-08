package com.jinaiya.tutorials.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jinaiya.tutorials.model.News;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * @author Jin
 * @date 2019/1/8
 */
public class NewsUtils {
    protected static final Logger logger = LoggerFactory.getLogger(NewsUtils.class);

    /**
     * 新闻api免费获取地址：
     * https://market.aliyun.com/products/57126001/cmapi013650.html?spm=5176.2020520132.101.9.69307218CJC43m#sku=yuncode765000000
     */
    private static final String APPCODE = "";

    public static List<News> getNews(String type) throws IOException {
        OkHttpClient client = new OkHttpClient();
        try {
            Request request = new Request
                    .Builder()
                    .url("http://toutiao-ali.juheapi.com/toutiao/index?type=" + type)
                    .header("Authorization", "APPCODE " + APPCODE)
                    .build();
            Response response = client.newCall(request).execute();

            JSONObject res = JSON.parseObject(response.body().string());

            List<News> list = JSON.parseArray(res.getJSONObject("result").get("data").toString(), News.class);
            return list;
        } catch (Exception e) {
            logger.error("NewsUtils getNews error ---> {}", e);
        }
        return null;
    }

    public static void main(String[] args) throws IOException{
        List<News> news = getNews("top");
        System.out.println("Title:" + news.get(1).getTitle()
                            + "\nAuthor:" + news.get(1).getAuthorName()
                            + "\nurl:" + news.get(1).getUrl());
    }
}
