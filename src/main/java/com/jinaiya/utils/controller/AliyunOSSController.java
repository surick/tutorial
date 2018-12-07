package com.jinaiya.utils.controller;

import com.jinaiya.utils.utils.AliyunOSSUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author Jin
 * @date 2018/12/7
 */
@Controller
@RequestMapping("/oss")
public class AliyunOSSController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/root")
    public String index() {
        return "index";
    }

    /**
     * 测试上传文件到阿里云OSS存储
     *
     * @return
     */
    @RequestMapping("/testUpload")
    @ResponseBody
    public String testUpload() {
        File file = new File("/Users/jin/Download/test.jpg");
        String url = AliyunOSSUtil.upLoad(file);
        System.out.println(url);
        return "success";
    }
    /**
     * 通过文件名下载文件
     */
    @RequestMapping("/testDownload")
    @ResponseBody
    public String testDownload() {
        AliyunOSSUtil.downloadFile(
                "test/2018-12-04/e3f892c27f07462a864a43b8187d4562-rawpixel-600782-unsplash.jpg","E:/Picture/e3f892c27f07462a864a43b8187d4562-rawpixel-600782-unsplash.jpg");
        return "success";
    }
    /**
     * 列出某个文件夹下的所有文件
     */
    @RequestMapping("/testListFile")
    @ResponseBody
    public String testListFile() {
        AliyunOSSUtil.listFile();
        return "success";
    }

    /**
     * 文件上传（供前端调用）
     */
    @RequestMapping(value = "/uploadFile")
    public String uploadPicture(@RequestParam("file") MultipartFile file, Model model) {
        logger.info("文件上传");
        String filename = file.getOriginalFilename();
        System.out.println(filename);
        try {

            if (file != null) {
                if (!"".equals(filename.trim())) {
                    File newFile = new File(filename);
                    FileOutputStream os = new FileOutputStream(newFile);
                    os.write(file.getBytes());
                    os.close();
                    file.transferTo(newFile);
                    // 上传到OSS
                    String uploadUrl = AliyunOSSUtil.upLoad(newFile);
                    model.addAttribute("url",uploadUrl);
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "success";
    }
}
