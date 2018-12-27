package com.jinaiya.tutorials.utils;

import net.sf.json.JSONObject;
import okhttp3.*;

import java.io.IOException;

/**
 * @author Jin
 * @date 2018/12/20
 */
public class WeatherUtil {

    private static final String APPKEY = "5j1znBVAsnSf5xQyNQyq";

    public static String getWeather(String lng, String lat) throws IOException {

        OkHttpClient client = new OkHttpClient();

        try {
            Request request = new Request.Builder()
                    .url("http://d7.weather.com.cn/fishing/api/v1/location?lon=" + lng + "&lat=" + lat)
                    .header("Authorization", "APPCODE ")
                    .build();
            Response response = client.newCall(request).execute();

            JSONObject locationObj = JSONObject.fromObject(response.body().string());
            String locationCode = locationObj.getJSONObject("result").getString("id");

            Request request1 = new Request.Builder()
                    .url("http://d7.weather.com.cn/fishing/api/v1/actual?id=" + locationCode)
                    .header("Authorization", "APPCODE ")
                    .build();
            Response response1 = client.newCall(request1).execute();

            return response1.body().string();
        } catch (Exception e) {
            e.printStackTrace();
            return "请求异常，请稍后重试";
        }

    }

    public static String getPM25(String city) {
        OkHttpClient client = new OkHttpClient();

        try {
            Request request = new Request.Builder()
                    .url("http://www.pm25.in/api/querys/pm2_5.json?city=" + city + "&token=" + APPKEY)
                    .build();
            Response response = client.newCall(request).execute();

            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
            return "获取失败";
        }
    }

    public static void main(String[] args) throws IOException{
        String weather = getWeather("120.5853", "31.2990");
        String pm25 = getPM25("苏州");
        System.out.println(weather);
        System.out.println("-----------");
        System.out.println(pm25);
    }
}
