package com.jinaiya.tutorials.utils;

import com.jinaiya.tutorials.model.dto.CommonRes;

/**
 * @author Jin
 * @date 2018/11/21
 */
public class ResultUtil {

    public static CommonRes success(Object o) {
        CommonRes commonRes = new CommonRes();
        commonRes.setCode(1);
        commonRes.setMsg("操作成功");
        commonRes.setData(o);
        return commonRes;
    }

    public static CommonRes success() {
        return success(null);
    }

    public static CommonRes error(Integer code, String msg) {
        CommonRes commonRes = new CommonRes();
        commonRes.setCode(code);
        commonRes.setMsg(msg);
        return commonRes;
    }
}
