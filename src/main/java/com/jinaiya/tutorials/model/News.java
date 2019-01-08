package com.jinaiya.tutorials.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author Jin
 * @date 2019/1/8
 */
public class News {

    private String title;

    private String date;

    @JSONField(name = "author_name")
    private String authorName;

    @JSONField(name = "thumbnail_pic_s")
    private String thumbnailPics;

    @JSONField(name = "thumbnail_pic_s02")
    private String thumbnailPics02;

    @JSONField(name = "thumbnail_pic_s03")
    private String thumbnailPics03;

    private String url;

    private String uniquekey;

    private String type;

    private String realtype;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getThumbnailPics() {
        return thumbnailPics;
    }

    public void setThumbnailPics(String thumbnailPics) {
        this.thumbnailPics = thumbnailPics;
    }

    public String getThumbnailPics02() {
        return thumbnailPics02;
    }

    public void setThumbnailPics02(String thumbnailPics02) {
        this.thumbnailPics02 = thumbnailPics02;
    }

    public String getThumbnailPics03() {
        return thumbnailPics03;
    }

    public void setThumbnailPics03(String thumbnailPics03) {
        this.thumbnailPics03 = thumbnailPics03;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUniquekey() {
        return uniquekey;
    }

    public void setUniquekey(String uniquekey) {
        this.uniquekey = uniquekey;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRealtype() {
        return realtype;
    }

    public void setRealtype(String realtype) {
        this.realtype = realtype;
    }
}
