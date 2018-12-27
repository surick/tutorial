package com.jinaiya.tutorials.service;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

/**
 * @author Jin
 * @date 2018/11/27
 */
public interface MyWebSocketService {
    void handleFrame(ChannelHandlerContext ctx, WebSocketFrame frame);
}
