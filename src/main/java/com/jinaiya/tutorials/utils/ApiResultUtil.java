package com.jinaiya.tutorials.utils;

import com.jinaiya.tutorials.model.dto.ApiRes;

/**
 * @author Jin
 * @date 2018/11/21
 */
public class ApiResultUtil {

    public static ApiRes success(String img) {
        ApiRes apiRes = new ApiRes();
        apiRes.setCode(1);
        apiRes.setMsg("操作成功");
        apiRes.setImg(img);
        return apiRes;
    }

    public static ApiRes error(String msg) {
        ApiRes apiRes = new ApiRes();
        apiRes.setCode(-1);
        apiRes.setMsg(msg);
        apiRes.setImg(null);
        return apiRes;
    }
}
