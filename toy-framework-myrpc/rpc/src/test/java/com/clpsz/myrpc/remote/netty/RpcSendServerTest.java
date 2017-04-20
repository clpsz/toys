package com.clpsz.myrpc.remote.netty;

import com.clpsz.myrpc.invoke.proxy.ClientProxyFactory;
import com.clpsz.myrpc.invoke.sample.SampleAddService;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by pp.zuo on 2017/4/19.
 */
public class RpcSendServerTest {
    @Test
    public void start() throws Exception {
        RpcSendServer rpcSendServer = new RpcSendServer();
        rpcSendServer.start();

        SampleAddService proxy = ClientProxyFactory.getNettyProxy(SampleAddService.class);
        int result = proxy.add(3, 3000);

        System.out.println(result);


        TimeUnit.SECONDS.sleep(3);
    }
}