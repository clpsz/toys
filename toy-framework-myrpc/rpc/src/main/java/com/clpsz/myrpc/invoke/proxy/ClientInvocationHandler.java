package com.clpsz.myrpc.invoke.proxy;

import org.apache.commons.lang3.reflect.MethodUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by peter.zuo on 2017/4/18.
 */
public class ClientInvocationHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        ImplPool implPool = ImplPool.getInstance();

        String className = method.getDeclaringClass().getName();
        Object bean = implPool.getImpl(className);
        String methodName = method.getName();

        return MethodUtils.invokeMethod(bean, methodName, args);
    }
}
