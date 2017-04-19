package com.clpsz.myrpc.remote.netty;

import com.clpsz.myrpc.invoke.model.RequestMsg;
import com.clpsz.myrpc.invoke.model.ResponseMsg;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.*;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by pp.zuo on 2017/4/19.
 */
public class ChannelSendHandler extends ChannelInboundHandlerAdapter {

    private Logger logger = LoggerFactory.getLogger(ChannelSendHandler.class);
    private ConcurrentHashMap<String, MsgCallBack> callBackMap = new ConcurrentHashMap<>();
    private Channel channel;

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        this.channel = ctx.channel();
        SendHandlerHolder.getInstance().setHandler(this);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf buf = (ByteBuf)msg;
//        System.out.println(ByteBufUtil.prettyHexDump(buf));
//        ReferenceCountUtil.release(msg);

        ResponseMsg responseMsg = (ResponseMsg) msg;
        MsgCallBack callBack = callBackMap.get(responseMsg.getMsgId());
        if (callBack != null) {
            callBackMap.remove(responseMsg.getMsgId());
            callBack.set(responseMsg);
        }
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    public MsgCallBack sendRequest(RequestMsg request) {
        MsgCallBack callBack = new MsgCallBack();
        callBackMap.put(request.getMsgId(), callBack);
        ChannelFuture future = null;
        try {
            future = channel.writeAndFlush(request).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return callBack;
    }

    public void close() {
        try {
            channel.close().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
