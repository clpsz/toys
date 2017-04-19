package com.clpsz.myrpc.remote.netty;

import com.clpsz.myrpc.invoke.model.RequestMsg;
import com.clpsz.myrpc.invoke.proxy.ClientProxyFactory;
import com.clpsz.myrpc.invoke.sample.SampleAddService;
import io.netty.handler.codec.serialization.ObjectEncoder;
import org.junit.Test;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by pp.zuo on 2017/4/19.
 */
public class RpcSendServerTest {
    @Test
    public void start() throws Exception {
        RpcSendServer rpcSendServer = new RpcSendServer();
        rpcSendServer.start();

        ChannelSendHandler handler = SendHandlerHolder.getInstance().getSendHandler();
        System.out.println(handler);
        RequestMsg request = new RequestMsg();
        request.setMsgId(UUID.randomUUID().toString());


        SampleAddService proxy = ClientProxyFactory.getNettyProxy(SampleAddService.class);
        int result = proxy.add(3, 114);

        System.out.println(result);
//
//        MsgCallBack msgCallback = handler.sendRequest(request);
//        Object result = msgCallback.get();
//        System.out.println(result);

//        handler.close();

        TimeUnit.SECONDS.sleep(3);
    }
}