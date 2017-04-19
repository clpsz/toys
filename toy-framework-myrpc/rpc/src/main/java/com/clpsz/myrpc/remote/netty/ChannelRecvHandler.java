package com.clpsz.myrpc.remote.netty;

import com.clpsz.myrpc.invoke.model.RequestMsg;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by pp.zuo on 2017/4/19.
 */
public class ChannelRecvHandler extends ChannelInboundHandlerAdapter{

    private Logger logger = LoggerFactory.getLogger(ChannelRecvHandler.class);
    private RecvExecutorService recvExecutorService = new RecvExecutorService();


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf buf = (ByteBuf)msg;
//        System.out.println(ByteBufUtil.prettyHexDump(buf));
//        ReferenceCountUtil.release(msg);

        RequestMsg requestMsg = (RequestMsg) msg;
        logger.info("get message{}", requestMsg);
        recvExecutorService.submit(ctx, requestMsg);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        logger.info("registered");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        logger.info("active");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
