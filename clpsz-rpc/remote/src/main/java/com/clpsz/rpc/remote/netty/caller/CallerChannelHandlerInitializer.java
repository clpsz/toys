package com.clpsz.rpc.remote.netty.caller;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * Created by pp.zuo on 2017/4/21.
 */
public class CallerChannelHandlerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        int lengthFieldLength = 4;

        ChannelPipeline pipeline = ch.pipeline();
        //inbound handler, 接收包时，执行顺序为从上到下
        pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, lengthFieldLength, 0, lengthFieldLength));
        pipeline.addLast(new ObjectDecoder(ClassResolvers.weakCachingResolver(this.getClass().getClassLoader())));
        pipeline.addLast(new CallerChannelHandler());

        //outbound handler，发送包时，执行顺序为从上到下
        pipeline.addFirst(new ObjectEncoder());
        pipeline.addFirst(new LengthFieldPrepender(lengthFieldLength));
    }
}
