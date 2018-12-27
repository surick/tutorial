package com.jinaiya.tutorials.service;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author Jin
 * @date 2018/11/27
 */
public interface MyHttpService {
    void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest request);
}
