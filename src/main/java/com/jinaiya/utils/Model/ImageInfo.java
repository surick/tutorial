package com.jinaiya.utils.Model;

/**
 * @author Jin
 * @date 2018/11/15
 */
public class ImageInfo {
    private String imageUrl;
    private String logoImageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLogoImageUrl() {
        return logoImageUrl;
    }

    public void setLogoImageUrl(String logoImageUrl) {
        this.logoImageUrl = logoImageUrl;
    }

    @Override
    public String toString() {
        return "ImageInfo{" +
                "imageUrl='" + imageUrl + '\'' +
                ", logoImageUrl='" + logoImageUrl + '\'' +
                '}';
    }
}
