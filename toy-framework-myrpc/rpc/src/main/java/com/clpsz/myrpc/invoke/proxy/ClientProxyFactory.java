package com.clpsz.myrpc.invoke.proxy;

import com.clpsz.myrpc.invoke.sample.SampleAddService;
import com.clpsz.myrpc.remote.netty.NettyInvocationHandler;

import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2017/4/18.
 */
public class ClientProxyFactory {
    @SuppressWarnings("unchecked")
    public static <T> T getProxy(Class<T> type) {
        return (T) Proxy.newProxyInstance(
                type.getClassLoader(),
                new Class<?>[]{type},
                new ClientInvocationHandler());
    }
    @SuppressWarnings("unchecked")
    public static <T> T getNettyProxy(Class<T> type) {
        return (T) Proxy.newProxyInstance(
                type.getClassLoader(),
                new Class<?>[]{type},
                new NettyInvocationHandler());
    }

}
