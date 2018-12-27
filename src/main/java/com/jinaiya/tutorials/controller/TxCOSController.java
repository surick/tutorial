package com.jinaiya.tutorials.controller;

import com.jinaiya.tutorials.utils.TxCOSUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;

/**
 * @author Jin
 * @date 2018/12/7
 */
@Controller
@RequestMapping("/tx/oss")
public class TxCOSController {

    private static final Logger logger = LoggerFactory.getLogger(TxCOSController.class);

    @RequestMapping("/testUpload")
    @ResponseBody
    public String testUpload() {
        File file = new File("/Users/jin/Downloads/test.jpg");
        String url = TxCOSUtil.upload(file);
        System.out.println(url);
        return "ok";
    }

    @RequestMapping("/testDownload")
    @ResponseBody
    public String testDownload() {
        String fileUrl = "/src/resources/static/downloads/snm.jpg";
        TxCOSUtil.downloadFile("test.jpg", fileUrl);
        return "ok";
    }

    @RequestMapping("/testDelete")
    @ResponseBody
    public String testDelete() {
        TxCOSUtil.deleteFile("test.jpg");
        return "ok";
    }
}
