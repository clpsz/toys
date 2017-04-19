package com.clpsz.myrpc.remote.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by pp.zuo on 2017/4/19.
 */
public class RpcRecvServer {

    private Logger logger = LoggerFactory.getLogger(RpcRecvServer.class);


    public void start() {
        String host = "localhost";
        int port = 9090;

        ServerBootstrap b = new ServerBootstrap();
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        b.group(boss, worker).channel(NioServerSocketChannel.class)
                .childHandler(new RpcRecvChannelInitializer())
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true);
        try {
            ChannelFuture future = b.bind(host, port).sync();
            logger.info("server started, {}:{}", host, port);
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
