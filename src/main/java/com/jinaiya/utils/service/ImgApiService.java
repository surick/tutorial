package com.jinaiya.utils.service;

import com.jinaiya.utils.model.ImgConfig;
import com.jinaiya.utils.model.dto.ApiRes;
import com.jinaiya.utils.model.dto.CommonRes;
import com.jinaiya.utils.utils.ApiResultUtil;
import com.jinaiya.utils.utils.ResultUtil;
import com.jinaiya.utils.utils.SogouApi;
import org.springframework.stereotype.Service;

/**
 * @author Jin
 * @date 2018/11/21
 */
@Service
public class ImgApiService {
    private ImgConfig ImgConfig = null;

    //上传
    public ApiRes doUpload(String key, String imgBase64){
        if (ImgConfig == null){
            getImgConfig();
        }


        if (!key.equals(ImgConfig.getKey())){
            return ApiResultUtil.error("通讯密钥错误");
        }


        if (ImgConfig.getType() == 1){
            String res = SogouApi.uploadImg(imgBase64);
            if (res.indexOf("http")>=0){
                return ApiResultUtil.success(res);
            }else{
                return ApiResultUtil.error("上传失败");
            }
        }


        return ApiResultUtil.error("类型错误");
    }



    //管理员登录
    public CommonRes login(String pass){
        if (ImgConfig == null){
            getImgConfig();
        }

        if (!pass.equals(ImgConfig.getAdmin())){
            return ResultUtil.error(-1,"管理员密码错误！");
        }
        return ResultUtil.success();
    }

    //拉取配置
    public CommonRes getConfig(){
        if (ImgConfig == null){
            getImgConfig();
        }

        return ResultUtil.success(ImgConfig);
    }

    //设置配置
    public CommonRes setConfig(ImgConfig newConfig){

        if (ImgConfig == null){
            getImgConfig();
        }

        ImgConfig = newConfig;


        return ResultUtil.success();
    }


    private ImgConfig getImgConfig(){
        if (ImgConfig == null){
            ImgConfig = new ImgConfig();
            ImgConfig.setAdmin("123456");
            ImgConfig.setSinaUser("");
            ImgConfig.setSinaPass("");
            ImgConfig.setKey("123456");
            ImgConfig.setType(1);
            ImgConfig.setSinaUpdateTime("");
        }

        return ImgConfig;
    }
}
