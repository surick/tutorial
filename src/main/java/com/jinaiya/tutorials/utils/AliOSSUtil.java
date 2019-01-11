package com.jinaiya.tutorials.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;
import com.jinaiya.tutorials.config.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Jin
 * @date 2018/12/7
 */
@SuppressWarnings(value = "unused")
public class AliOSSUtil {

    private static final Logger logger = LoggerFactory.getLogger(AliOSSUtil.class);

    private static String FILE_URL;
    private static String bucketName = Const.BUCKET_NAME;
    private static String endpoint = Const.END_POINT;
    private static String accessKeyId = Const.ACCESSKEY_ID;
    private static String accessKeySecret = Const.ACCESSKEY_SECRET;
    private static String fileHost = Const.FILE_HOST;


    public static OSSClient getOSSClient(){
        return new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }

    /**
     * 创建存储空间
     *
     * @param ossClient  OSS连接
     * @param bucketName 存储空间
     * @return
     */
    public static String createBucketName(OSSClient ossClient, String bucketName) {
        //存储空间
        final String bucketNames = bucketName;
        if (!ossClient.doesBucketExist(bucketName)) {
            //创建存储空间
            Bucket bucket = ossClient.createBucket(bucketName);
            logger.info("创建存储空间成功");
            return bucket.getName();
        }
        return bucketNames;
    }

    /**
     * 删除存储空间buckName
     *
     * @param ossClient  oss对象
     * @param bucketName 存储空间
     */
    public static void deleteBucket(OSSClient ossClient, String bucketName) {
        ossClient.deleteBucket(bucketName);
        logger.info("删除" + bucketName + "Bucket成功");
    }

    /**
     * 创建模拟文件夹
     *
     * @param ossClient  oss连接
     * @param bucketName 存储空间
     * @param folder     模拟文件夹名如"qj_nanjing/"
     * @return 文件夹名
     */
    public static String createFolder(OSSClient ossClient, String bucketName, String folder) {
        //文件夹名
        final String keySuffixWithSlash = folder;
        //判断文件夹是否存在，不存在则创建
        if (!ossClient.doesObjectExist(bucketName, keySuffixWithSlash)) {
            //创建文件夹
            ossClient.putObject(bucketName, keySuffixWithSlash, new ByteArrayInputStream(new byte[0]));
            logger.info("创建文件夹成功");
            //得到文件夹名
            OSSObject object = ossClient.getObject(bucketName, keySuffixWithSlash);
            String fileDir = object.getKey();
            return fileDir;
        }
        return keySuffixWithSlash;
    }

    /**
     * 上传文件
     * @param file 需要上传的文件路径
     * @return 如果上传的文件是图片的话，会返回图片的"URL"，如果非图片的话会返回"非图片，不可预览。文件路径为：+文件路径"
     */
    public static String upLoad(File file) {
        // 默认值为：true
        boolean isImage = true;
        // 判断所要上传的图片是否是图片，图片可以预览，其他文件不提供通过URL预览
        try {
            Image image = ImageIO.read(file);
            isImage = image == null ? false : true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.info("------OSS upload--------" + file.getName());

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = format.format(new Date());

        // 判断文件
        if (file == null) {
            return null;
        }
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {
            // 判断容器是否存在,不存在就创建
            if (!ossClient.doesBucketExist(bucketName)) {
                ossClient.createBucket(bucketName);
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                ossClient.createBucket(createBucketRequest);
            }
            // 设置文件路径和名称
            String fileUrl = fileHost + "/" + (dateStr + "/" + UUID.randomUUID().toString().replace("-", "") + "-" + file.getName());

            if (isImage) {
                FILE_URL = "https://" + bucketName + "." + endpoint + "/" + fileUrl;
            } else {
                FILE_URL = "非图片，不可预览。文件路径为：" + fileUrl;
            }

            // 上传文件
            PutObjectResult result = ossClient.putObject(new PutObjectRequest(bucketName, fileUrl, file));
            // 设置权限(公开读)
            ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            if (result != null) {
                logger.info("------OSS upload success------" + fileUrl);
            }
        } catch (OSSException oe) {
            logger.error(oe.getMessage());
        } catch (ClientException ce) {
            logger.error(ce.getErrorMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return FILE_URL;
    }


    /**
     * 通过文件名下载文件
     *
     * @param objectName    要下载的文件名
     * @param localFileName 本地要创建的文件名
     */
    public static void downloadFile(String objectName, String localFileName) {

        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建
        ossClient.getObject(new GetObjectRequest(bucketName, objectName), new File(localFileName));
        // 关闭OSSClient
        ossClient.shutdown();
    }

    /**
     * 列举 test 文件下所有的文件
     */
    public static List listFile() {
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {
            ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucketName);

            // 设置prefix参数来获取fun目录下的所有文件
            listObjectsRequest.setPrefix("test/");
            // 列出文件
            ObjectListing listing = ossClient.listObjects(listObjectsRequest);
            return listing.getObjectSummaries().stream()
                    .filter(item -> item.getKey().lastIndexOf(".") > 0)
                    .map(item -> "https://insonaimage.oss-cn-beijing.aliyuncs.com/" + item.getKey())
                    .collect(Collectors.toList());
            // 遍历所有文件
//            System.out.println("Objects:");
//            for (OSSObjectSummary objectSummary : listing.getObjectSummaries()) {
//                System.out.println(objectSummary.getKey());
//            }
            // 遍历所有commonPrefix。
//            System.out.println("CommonPrefixes:");
//            for (String commonPrefix : listing.getCommonPrefixes()) {
//                System.out.println(commonPrefix);
//            }
        } finally {
            // 关闭OSSClient。
            ossClient.shutdown();
        }
    }

    /**
     * 根据key删除OSS服务器上的文件
     *
     * @param bucketName 存储空间
     * @param folder     模拟文件夹名 如"qj_nanjing/"
     * @param key        Bucket下的文件的路径名+文件名 如："upload/cake.jpg"
     */
    public static void deleteFile(String bucketName, String folder, String key) {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        ossClient.deleteObject(bucketName, folder + key);
        logger.info("删除" + bucketName + "下的文件" + folder + key + "成功");
    }

    /**
     *
     * @Title: uploadByNetworkStream
     * @Description: 通过网络流上传文件
     * @param ossClient 	oss客户端
     * @param url 			URL
     * @param bucketName 	bucket名称
     * @param objectName 	上传文件目录和（包括文件名）例如“test/index.html”
     * @return void 		返回类型
     * @throws
     */
    public static void uploadByNetworkStream(OSSClient ossClient, URL url, String bucketName, String objectName) {
        try {
            InputStream inputStream = url.openStream();
            ossClient.putObject(bucketName, objectName, inputStream);
            ossClient.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    /**
     *
     * @Title: uploadByInputStream
     * @Description: 通过输入流上传文件
     * @param ossClient 	oss客户端
     * @param inputStream 	输入流
     * @param bucketName 	bucket名称
     * @param objectName 	上传文件目录和（包括文件名） 例如“test/a.jpg”
     * @return void 		返回类型
     * @throws
     */public static void uploadByInputStream(OSSClient ossClient, InputStream inputStream, String bucketName,
                                              String objectName) {
        try {
            ossClient.putObject(bucketName, objectName, inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @Title: uploadByFile
     * @Description: 通过file上传文件
     * @param ossClient 	oss客户端
     * @param file 			上传的文件
     * @param bucketName 	bucket名称
     * @param objectName 	上传文件目录和（包括文件名） 例如“test/a.jpg”
     * @return void 		返回类型
     * @throws
     */
    public static void uploadByFile(OSSClient ossClient, File file, String bucketName, String objectName) {
        try {
            ossClient.putObject(bucketName, objectName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @Title: deleteFile
     * @Description: 根据key删除oss服务器上的文件
     * @param ossClient		oss客户端
     * @param bucketName		bucket名称
     * @param key    		文件路径/名称，例如“test/a.txt”
     * @return void    		返回类型
     * @throws
     */
    public static void deleteFile(OSSClient ossClient, String bucketName, String key) {
        ossClient.deleteObject(bucketName, key);
    }

    /**
     *
     * @Title: getInputStreamByOSS
     * @Description:根据key获取服务器上的文件的输入流
     * @param ossClient 	oss客户端
     * @param bucketName 	bucket名称
     * @param key 			文件路径和名称
     * @return InputStream 	文件输入流
     * @throws
     */
    public static InputStream getInputStreamByOSS(OSSClient ossClient, String bucketName, String key) {
        InputStream content = null;
        try {
            OSSObject ossObj = ossClient.getObject(bucketName, key);
            content = ossObj.getObjectContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

    /**
     *
     * @Title: queryAllObject
     * @Description: 查询某个bucket里面的所有文件
     * @param ossClient		oss客户端
     * @param bucketName		bucket名称
     * @return List<String>  文件路径和大小集合
     * @throws
     */
    public static List<String> queryAllObject(OSSClient ossClient, String bucketName) {
        List<String> results = new ArrayList<String>();
        try {
            // ossClient.listObjects返回ObjectListing实例，包含此次listObject请求的返回结果。
            ObjectListing objectListing = ossClient.listObjects(bucketName);
            // objectListing.getObjectSummaries获取所有文件的描述信息。
            for (OSSObjectSummary objectSummary : objectListing.getObjectSummaries()) {
                // objectSummary.getSize();
                results.add(objectSummary.getKey());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

}
