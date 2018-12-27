package com.jinaiya.tutorials.utils;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.storage.model.FileListing;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Jin
 * @date 2018/12/12
 */
public class QiniuUtil {

    protected static final Logger logger = LoggerFactory.getLogger(QiniuUtil.class);

    private static String ACCESS_KEY = "";
    private static String SECRET_KEY = "";
    private static String BUCKET_NAME = "surick";
    private static String HOST = "http://pjm9s2rrt.bkt.clouddn.com/";

    /**
     * 简单上传，使用默认策略，只需要设置上传的空间名就可以了
     */
//    public String getUpToken() {
//        return auth.uploadToken(BUCKET_NAME);
//    }

    public static String upload(File file) throws IOException {

        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

        Zone z = Zone.autoZone();
        Configuration c = new Configuration(z);

        UploadManager uploadManager = new UploadManager(c);

        try {
            //调用put方法上传
            String suffix = file.getName().substring(file.getName().lastIndexOf("."));
            String newName = UUID.randomUUID().toString().replace("-", "") + suffix;
            Response res = uploadManager.put(file, newName, auth.uploadToken(BUCKET_NAME));
            //打印返回的信息
            System.out.println(res.bodyString());
            return HOST + newName;
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                //响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }
        return "upload error";
    }

    public static boolean delete(String key) {
        boolean flag = false;

        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

//        Zone z = Zone.zone0();
        Zone z = Zone.autoZone();
        Configuration c = new Configuration(z);

        //实例化一个BucketManager对象
        BucketManager bucketManager = new BucketManager(auth, c);

        try {
            //调用delete方法移动文件
            bucketManager.delete(BUCKET_NAME, key);
            flag = true;
        } catch (QiniuException e) {
            //捕获异常信息
            Response r = e.response;
            System.out.println(r.toString());
            flag = false;
        }
        return flag;
    }

    public static List list() {
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

        Zone z = Zone.autoZone();
        Configuration c = new Configuration(z);

        //实例化一个BucketManager对象
        BucketManager bucketManager = new BucketManager(auth, c);

        try {
            //调用listFiles方法列举指定空间的指定文件
            //参数一：bucket    空间名
            //参数二：prefix    文件名前缀
            //参数三：marker    上一次获取文件列表时返回的 marker
            //参数四：limit     每次迭代的长度限制，最大1000，推荐值 100
            //参数五：delimiter 指定目录分隔符，列出所有公共前缀（模拟列出目录效果）。缺省值为空字符串
            FileListing fileListing = bucketManager.listFiles(BUCKET_NAME, null, null, 10, null);
            FileInfo[] items = fileListing.items;
            return Arrays.stream(items).map(item -> HOST + item.key).collect(Collectors.toList());
//            for (FileInfo fileInfo : items) {
//                System.out.println(fileInfo.key);
//            }
        } catch (QiniuException e) {
            //捕获异常信息
            Response r = e.response;
            System.out.println(r.toString());
        }
        return null;
    }

    public static void main(String[] args) {
//        File file = new File("/Users/jin/Downloads/1.jpg");
//        String url = "";
//        try {
//            url = upload(file);
//            System.out.println(url);
//        } catch (IOException e) {
//            logger.error("upload error-->{}", e);
//        }
        List list = list();
        list.stream().forEach(System.out::println);
//        System.out.println(list.toString());
    }
}
