package com.jinaiya.utils.utils;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Jin
 * @date 2018/11/21
 */
public class SogouApi {

    public static String uploadImg(String imgBase64) {
        String url = "http://pic.sogou.com/pic/upload_pic.jsp";

        if (Base64.decode(imgBase64) == null) {
            return "";
        }
        byte[] postData;

        byte[] temp = unitByteArray(Base64.decode("LS0tLS0tV2ViS2l0Rm9ybUJvdW5kYXJ5R0xmR0IwSGdVTnRwVFQxaw0KQ29udGVudC1EaXNwb3NpdGlvbjogZm9ybS1kYXRhOyBuYW1lPSJwaWNfcGF0aCI7IGZpbGVuYW1lPSIxMS5wbmciDQpDb250ZW50LVR5cGU6IGltYWdlL3BuZw0KDQo=")
                , Base64.decode(imgBase64));

        postData = unitByteArray(temp, Base64.decode("DQotLS0tLS1XZWJLaXRGb3JtQm91bmRhcnlHTGZHQjBIZ1VOdHBUVDFrLS0NCg=="));

        String ret = "";

        URL u = null;
        HttpURLConnection con = null;
        InputStream inputStream = null;
        //尝试发送请求
        try {
            u = new URL(url);
            con = (HttpURLConnection) u.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type", "multipart/form-data; boundary=----WebKitFormBoundaryGLfGB0HgUNtpTT1k");
            con.setRequestProperty("Content-Length", String.valueOf(postData.length));

            OutputStream outStream = con.getOutputStream();
            outStream.write(postData);
            outStream.flush();
            outStream.close();
            //读取返回内容
            inputStream = con.getInputStream();


            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader bufr = new BufferedReader(isr);
            String str;



            while ((str = bufr.readLine()) != null) {
                ret+=str;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return ret;
        }


    }

    /**
     * 合并byte数组
     * @param byte1
     * @param byte2
     * @return
     */
    public static byte[] unitByteArray(byte[] byte1, byte[] byte2) {
        byte[] unitByte = new byte[byte1.length + byte2.length];
        System.arraycopy(byte1, 0, unitByte, 0, byte1.length);
        System.arraycopy(byte2, 0, unitByte, byte1.length, byte2.length);
        return unitByte;
    }

    /**
     * 去两个文本之间的文本值
     * @param text
     * @param left
     * @param right
     * @return
     */
    public static String getSubString(String text, String left, String right) {
        String result = "";
        int zLen;
        if (left == null || left.isEmpty()) {
            zLen = 0;
        } else {
            zLen = text.indexOf(left);
            if (zLen > -1) {
                zLen += left.length();
            } else {
                zLen = 0;
            }
        }
        int yLen = text.indexOf(right, zLen);
        if (yLen < 0 || right == null || right.isEmpty()) {
            yLen = text.length();
        }
        result = text.substring(zLen, yLen);
        return result;
    }
}
