package com.jinaiya.utils.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Jin
 * @date 2018/11/15
 */
public interface ImageUploadService {
    /**
     * 图片上传
     * @param file
     * @param uploadPath 服务器上上传文件的路径
     * @param physicalUploadPath 服务器上上传文件的物理路径
     * @return 上传文件URL相对地址
     */
    String uploadImage(MultipartFile file, String uploadPath, String physicalUploadPath);
}
