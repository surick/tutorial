package com.jinaiya.utils.model;

/**
 * @author Jin
 * @date 2018/11/21
 */
public class ImgConfig {
    private String admin;
    private String sinaUser;
    private String sinaPass;
    private String key;
    private Integer type;
    private String sinaUpdateTime;

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getSinaUser() {
        return sinaUser;
    }

    public void setSinaUser(String sinaUser) {
        this.sinaUser = sinaUser;
    }

    public String getSinaPass() {
        return sinaPass;
    }

    public void setSinaPass(String sinaPass) {
        this.sinaPass = sinaPass;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getSinaUpdateTime() {
        return sinaUpdateTime;
    }

    public void setSinaUpdateTime(String sinaUpdateTime) {
        this.sinaUpdateTime = sinaUpdateTime;
    }

    @Override
    public String toString() {
        return "ImgConfig{" +
                "admin='" + admin + '\'' +
                ", sinaUser='" + sinaUser + '\'' +
                ", sinaPass='" + sinaPass + '\'' +
                ", key='" + key + '\'' +
                ", type=" + type +
                ", sinaUpdateTime='" + sinaUpdateTime + '\'' +
                '}';
    }
}
