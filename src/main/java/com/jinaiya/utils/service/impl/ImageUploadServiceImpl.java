package com.jinaiya.utils.service.impl;

import com.jinaiya.utils.service.ImageUploadService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author Jin
 * @date 2018/11/15
 */
@Service
public class ImageUploadServiceImpl implements ImageUploadService {
    protected static final Logger logger = LoggerFactory.getLogger(ImageUploadServiceImpl.class);

    @Override
    public String uploadImage(MultipartFile file, String uploadPath, String physicalUploadPath) {
        String filePath = physicalUploadPath + file.getOriginalFilename();
        try {
            File targetFile = new File(filePath);
            FileUtils.writeByteArrayToFile(targetFile, file.getBytes());
        } catch (IOException e) {
            logger.error("upload image error:{}", e);
            e.printStackTrace();
        }
        return uploadPath + "/" + file.getOriginalFilename();
    }
}
