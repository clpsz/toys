package com.clpsz.myrpc.remote.netty;

import com.clpsz.myrpc.invoke.model.RequestMsg;
import com.clpsz.myrpc.invoke.model.ResponseMsg;
import com.clpsz.myrpc.invoke.proxy.ImplPool;
import com.clpsz.myrpc.util.InvokeUtil;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by pp.zuo on 2017/4/19.
 */
public class RecvExecutorService {
    Logger logger = LoggerFactory.getLogger(RecvExecutorService.class);
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    private final ImplPool implPool = ImplPool.getInstance();


    public void submit(final ChannelHandlerContext ctx, final RequestMsg request) {
        MyRunnable runnable = new MyRunnable(ctx, request);
        //TODO: 直接只是提交会吃掉异常
        executorService.submit(runnable);

    }

    private class MyRunnable implements Runnable {
        private final ChannelHandlerContext ctx;
        private final RequestMsg request;


        public MyRunnable(ChannelHandlerContext ctx, RequestMsg request) {
            this.ctx = ctx;
            this.request = request;
        }

        @Override
        public void run() {
            ResponseMsg response = InvokeUtil.invokeMethod(implPool, request);
            //使用channel写入返回数据，从第一个handler开始过，确保返回数据一定会经过编码
            ChannelFuture future = ctx.channel().writeAndFlush(response);
            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    logger.info("rpc response send back finish");
                }
            });
        }
    }
}
