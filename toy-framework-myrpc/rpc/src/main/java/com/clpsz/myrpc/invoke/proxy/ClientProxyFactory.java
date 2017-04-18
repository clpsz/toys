package com.clpsz.myrpc.invoke.proxy;

import com.clpsz.myrpc.invoke.sample.SampleAddService;

import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2017/4/18.
 */
public class ClientProxyFactory {
    public SampleAddService getProxy() {
        return (SampleAddService)Proxy.newProxyInstance(SampleAddService.class.getClassLoader()
                , new Class<?>[]{SampleAddService.class}, new ClientInvocationHandler());
    }
}
