package com.jinaiya.utils.model.dto;

/**
 * @author Jin
 * @date 2018/11/21
 */
public class ApiRes {
    private Integer code;
    private String msg;
    private String img;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "ApiRes{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
