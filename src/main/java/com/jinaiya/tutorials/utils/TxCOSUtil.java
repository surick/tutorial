package com.jinaiya.tutorials.utils;

import com.jinaiya.tutorials.config.Const;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.GetObjectRequest;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author Jin
 * @date 2018/12/7
 */
public class TxCOSUtil {

    private static final Logger logger = LoggerFactory.getLogger(TxCOSUtil.class);

    public static String upload(File file) {

        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(Const.SECRET_ID, Const.SECRET_KEY);
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig中包含了设置region, https(默认http), 超时, 代理等set方法, 使用可参见源码或者接口文档FAQ中说明
        ClientConfig clientConfig = new ClientConfig(new Region(Const.REGION));
        // 3 生成cos客户端
        COSClient cosClient = new COSClient(cred, clientConfig);

        String url = "";
        try {
            // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
            String bucketName = Const.TX_BUCKET_NAME;
            String key = file.getName();
            PutObjectResult putObjectResult = cosClient.putObject(bucketName, key, file);
            // 获取文件的 etag
//            url = putObjectResult.getETag();
            url = "https://" + bucketName + ".cos-website." + Const.REGION + ".myqcloud.com/" + key;
        } catch (Exception e) {
            logger.error("qcloud upload file error--->{}", e);
        } finally {
            if (cosClient != null) {
                cosClient.shutdown();
            }
        }
        return url;
    }

    public static void downloadFile(String key, String localFileName) {
        COSCredentials cred = new BasicCOSCredentials(Const.SECRET_ID, Const.SECRET_KEY);
        ClientConfig clientConfig = new ClientConfig(new Region(Const.REGION));
        COSClient cosClient = new COSClient(cred, clientConfig);

        String bucketName = Const.TX_BUCKET_NAME;
        File downFile = new File(localFileName);
        GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, key);
        ObjectMetadata downObjectMeta = cosClient.getObject(getObjectRequest, downFile);

        cosClient.shutdown();
    }

    public static boolean deleteFile(String key) {
        boolean flag = true;

        COSCredentials cred = new BasicCOSCredentials(Const.SECRET_ID, Const.SECRET_KEY);
        ClientConfig clientConfig = new ClientConfig(new Region(Const.REGION));
        COSClient cosClient = new COSClient(cred, clientConfig);

        String bucketName = Const.TX_BUCKET_NAME;

        try {
            cosClient.deleteObject(bucketName, key);
        } catch (Exception e) {
            flag = false;
            logger.error("qcloud delete file error ----> {}", e);
        } finally {
            if (cosClient != null) {
                cosClient.shutdown();
            }
        }
        return flag;
    }
}
