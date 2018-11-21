package com.jinaiya.utils.controller;

import com.jinaiya.utils.model.ImageInfo;
import com.jinaiya.utils.service.ImageUploadService;
import com.jinaiya.utils.service.ImageWatermarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author Jin
 * @date 2018/11/15
 */
@RestController
public class WatermarkController {

    @Autowired
    private ImageUploadService imageUploadService;

    @Autowired
    private ImageWatermarkService imageWatermarkService;

    @PostMapping("/watermarktest")
    public ImageInfo watermarkTest(@RequestParam("file") MultipartFile image) {
        ImageInfo imageInfo = new ImageInfo();
        // 服务器上上传文件的相对路径
        String uploadPath = "static/images/";
        // 服务器上上传文件的物理路径
///        String physicalUploadPath = getClass().getClassLoader().getResource(uploadPath).getPath();
        String physicalUploadPath = "/Users/jin/Downloads";

        String imageURL = imageUploadService.uploadImage(image, uploadPath, physicalUploadPath);
        File imageFile = new File(physicalUploadPath + image.getOriginalFilename());

        String watermarkAddImageURL = imageWatermarkService.watermarkAdd(imageFile,
                image.getOriginalFilename(), uploadPath, physicalUploadPath);
        imageInfo.setImageUrl(imageURL);
        imageInfo.setLogoImageUrl(watermarkAddImageURL);
        return imageInfo;
    }
}
