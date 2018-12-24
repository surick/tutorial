package com.jinaiya.utils.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @author Jin
 * @date 2018/12/24
 */
public class MapUtil {
    public static final String BAIDU_MAP_AK = "";

    public static final String GETADDRESS = "http://api.map.baidu.com/geocoder/v2/?callback=renderReverse&location={latitude},{longitude}&output=json&ak=" + BAIDU_MAP_AK;

    public static String getAddress(BigDecimal longitude, BigDecimal latitude) {

        OkHttpClient client = new OkHttpClient();

        try {
            Request request = new Request.Builder()
                    .url(GETADDRESS.replace("{longitude}",longitude.toString()).replace("{latitude}", latitude.toString()))
                    .build();

            Response response = client.newCall(request).execute();

            String result = response.body().string();

            result = result.substring(result.indexOf("{"),result.lastIndexOf("}")+1);
            JSONObject jsonObject = JSON.parseObject(result);
            if (jsonObject != null && Integer.valueOf(jsonObject.get("status").toString()) == 0) {
                Map resultMap = (Map)jsonObject.get("result");
                String fmtAddress = resultMap.get("formatted_address").toString();
                String desc = resultMap.get("sematic_description").toString();
                return fmtAddress + "\n" + desc;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String... args) throws IOException{
        String res = getAddress(new BigDecimal(120.5853), new BigDecimal(31.2990));
        System.out.println(res);
    }
}
