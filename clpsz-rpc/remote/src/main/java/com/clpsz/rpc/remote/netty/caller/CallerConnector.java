package com.clpsz.rpc.remote.netty.caller;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by pp.zuo on 2017/4/21.
 */
public class CallerConnector {
    private Logger logger = LoggerFactory.getLogger(CallerConnector.class);


    public void start() {
        String host = "localhost";
        int port = 9090;

        Bootstrap b = new Bootstrap();
        EventLoopGroup eventLoop = new NioEventLoopGroup();
        b.group(eventLoop).channel(NioSocketChannel.class)
                .handler(new CallerChannelHandlerInitializer())
                .option(ChannelOption.SO_KEEPALIVE, true);
        try {
            b.connect(host, port).sync();
            logger.info("clpsz rpc client connected on {}:{}!", host, port);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventLoop.shutdownGracefully();
        }
    }
}
