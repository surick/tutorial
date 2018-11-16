package com.jinaiya.utils.service;

import java.io.File;

/**
 * @author Jin
 * @date 2018/11/15
 */
public interface ImageWatermarkService {
    /**
     * 图片加水印
     * @param imgFile 图像文件
     * @param imageFileName 图像文件名
     * @param uploadPath 服务器上上传文件的相对路径
     * @param realUploadPath 服务器上上传文件的物理路径
     * @return
     */
    String watermarkAdd(File imgFile, String imageFileName, String uploadPath, String realUploadPath);
}
