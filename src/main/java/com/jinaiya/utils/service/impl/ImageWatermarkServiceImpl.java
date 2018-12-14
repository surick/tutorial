//package com.jinaiya.utils.service.impl;
//
//import com.jinaiya.utils.model.Const;
//import com.jinaiya.utils.service.ImageWatermarkService;
//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//
///**
// * @author Jin
// * @date 2018/11/15
// */
//@Service
//public class ImageWatermarkServiceImpl implements ImageWatermarkService {
//    protected static final Logger logger = LoggerFactory.getLogger(ImageWatermarkServiceImpl.class);
//
//    @Override
//    public String watermarkAdd(File imgFile, String imageFileName, String uploadPath, String realUploadPath) {
//        String imgWithWatermarkFileName = "watermark_" + imageFileName;
//        OutputStream os = null;
//        try {
//            Image image = ImageIO.read(imgFile);
//
//            int width = image.getWidth(null);
//            int height = image.getHeight(null);
//
//            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//            Graphics2D g = bufferedImage.createGraphics();
//            g.drawImage(image, 0, 0, width, height, null);
//
//            // 水印图片地址
//            String logoPath = realUploadPath + "/" + Const.LOGO_FILE_NAME;
//            // 读取水印图片
//            File logo = new File(logoPath);
//            Image imageLogo = ImageIO.read(logo);
//
//            // 水印图片的宽度和高度
//            int markWidth = imageLogo.getWidth(null);
//            int markHeight = imageLogo.getHeight(null);
//
//            // 设置水印透明度
//            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, Const.ALPHA));
//            // 设置水印图片的旋转度
//            g.rotate(Math.toRadians(-10), bufferedImage.getWidth() / 2, bufferedImage.getHeight() / 2);
//
//            int x = Const.X;
//            int y = Const.Y;
//
//            int xInterval = Const.X_INTERVAL;
//            int yInterval = Const.Y_INTERVAL;
//
//            // 循环添加多个水印logo
//            double count = 1.5;
//            while (x < width * count) {
//                y = -height / 2;
//                while (y < height * count) {
//                    g.drawImage(imageLogo, x, y, null);
//                    y += markHeight + yInterval;
//                }
//                x += markWidth + xInterval;
//            }
//
//            g.dispose();
//
//            os = new FileOutputStream(realUploadPath + "/" + imgWithWatermarkFileName);
//            JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os);
//            en.encode(bufferedImage);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (os != null) {
//                try {
//                    os.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        return uploadPath + "/" + imgWithWatermarkFileName;
//    }
//}
