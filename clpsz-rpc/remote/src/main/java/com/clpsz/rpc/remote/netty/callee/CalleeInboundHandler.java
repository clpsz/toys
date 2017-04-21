package com.clpsz.rpc.remote.netty.callee;

import com.clpsz.rpc.entity.RpcRequest;
import com.clpsz.rpc.entity.RpcResponse;
import com.clpsz.rpc.util.MyMethodInvokeUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by pp.zuo on 2017/4/21.
 */
public class CalleeInboundHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        RpcRequest request = (RpcRequest) msg;
        RpcResponse response = MyMethodInvokeUtil.invoke(request);
        ctx.channel().writeAndFlush(response);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
