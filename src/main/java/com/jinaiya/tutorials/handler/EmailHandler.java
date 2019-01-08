package com.jinaiya.tutorials.handler;

import com.jinaiya.tutorials.utils.AliEmailUtil;
import org.springframework.stereotype.Component;

/**
 * @author Jin
 * @date 2019/1/8
 */
@Component
public class EmailHandler {

    public boolean process(String full) {
        try {
            AliEmailUtil.sendEmail(full);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
