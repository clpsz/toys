package com.clpsz.myrpc.remote.netty;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by pp.zuo on 2017/4/19.
 */
public class RpcSendServer {

    Logger logger = LoggerFactory.getLogger(RpcSendServer.class);


    public void start() {
        String host = "localhost";
        int port = 9090;

        Bootstrap b = new Bootstrap();
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        b.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new RpcSendChannelInitializer());
        try {
            ChannelFuture future = b.connect(host, port).sync();
            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    logger.info("connected");
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
