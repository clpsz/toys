package com.clpsz.rpc.remote.netty.callee;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by pp.zuo on 2017/4/21.
 */
public class CalleeServerLoader {
    private Logger logger = LoggerFactory.getLogger(CalleeServerLoader.class);

    public void start() {
        String host = "localhost";
        int port = 9090;

        ServerBootstrap b = new ServerBootstrap();
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

        b.group(boss, worker).channel(NioServerSocketChannel.class)
                .childHandler(new CalleeChannelHandlerInitializer())
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true);

        try {
            ChannelFuture f = b.bind(host, port).sync();
            logger.info("clpsz rpc server started on {}:{}!", host, port);
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
