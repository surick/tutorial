package com.jinaiya.utils.model;

import java.io.Serializable;

/**
 * @author Jin
 * @date 2018/11/21
 */
public class DingTalkMsgTemplate implements Serializable {
    private String msgtype;
    private TextBean text;

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public TextBean getText() {
        return text;
    }

    public void setText(TextBean text) {
        this.text = text;
    }

    public static class TextBean {
        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
