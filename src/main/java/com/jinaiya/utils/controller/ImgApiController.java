package com.jinaiya.utils.controller;

import com.google.gson.Gson;
import com.jinaiya.utils.model.ImgConfig;
import com.jinaiya.utils.model.dto.ApiRes;
import com.jinaiya.utils.model.dto.CommonRes;
import com.jinaiya.utils.service.ImgApiService;
import com.jinaiya.utils.utils.ApiResultUtil;
import com.jinaiya.utils.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Jin
 * @date 2018/11/21
 */
@RestController
public class ImgApiController {
    @Autowired
    ImgApiService imgApiService;

    @RequestMapping("/api")
    public void api(String key, String imgBase64, String onlyUrl, HttpServletResponse rsp) throws IOException {
        rsp.setHeader("Content-Type", "application/json;charset=UTF-8");

        boolean str = false;
        if (onlyUrl!=null && onlyUrl.equals("1")){
            str = true;
        }

        if (key==null || key.equals("")){
            if (str){
                rsp.getWriter().print("null");
            }else{
                rsp.getWriter().print(new Gson().toJson(ApiResultUtil.error("请传入通讯密钥")));
            }

        }
        if (imgBase64 == null || imgBase64.equals("")){
            if (str){
                rsp.getWriter().print("null");
            }else{
                rsp.getWriter().print(new Gson().toJson(ApiResultUtil.error("请传入图片的Base64编码")));
            }
        }

        ApiRes apiRes = imgApiService.doUpload(key, imgBase64);

        if (str){
            rsp.getWriter().print(apiRes.getImg());
        }else{
            rsp.getWriter().print(new Gson().toJson(apiRes));
        }

    }

    @RequestMapping("/login")
    public CommonRes login(String pass,HttpSession session){

        CommonRes res = imgApiService.login(pass);
        if (res.getCode()==1){
            session.setAttribute("user","admin");
        }
        return res;
    }

    @RequestMapping("/getConfig")
    public CommonRes getConfig(HttpSession session){
        if (session.getAttribute("user") == null){
            return ResultUtil.error(-1,"请登陆！");
        }
        return imgApiService.getConfig();
    }

    @RequestMapping("/setConfig")
    public CommonRes setConfig(HttpSession session, ImgConfig pimgConfig){
        if (session.getAttribute("user") == null){
            return ResultUtil.error(-1,"请登陆！");
        }
        return imgApiService.setConfig(pimgConfig);
    }

    @RequestMapping("/loginOut")
    public CommonRes loginOut(HttpSession session){
        session.removeAttribute("user");
        return ResultUtil.success();
    }


}
