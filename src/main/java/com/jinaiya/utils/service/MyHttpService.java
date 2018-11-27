package com.jinaiya.utils.service;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author Jin
 * @date 2018/11/27
 */
public interface MyHttpService {
    void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest request);
}
