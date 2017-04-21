package com.clpsz.rpc.remote.netty.invoke;

import java.lang.reflect.Proxy;

/**
 * Created by pp.zuo on 2017/4/20.
 */
public class DynamicInvokeProxyFactory {
    @SuppressWarnings("unchecked")
    public static <T> T getProxy(Class<T> interfaceName) {
        return (T)Proxy.newProxyInstance(interfaceName.getClassLoader(),
                new Class<?>[]{interfaceName}, new DynamicInvokeHandler());
    }
}
