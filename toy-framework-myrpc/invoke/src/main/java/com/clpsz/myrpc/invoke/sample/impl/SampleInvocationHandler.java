package com.clpsz.myrpc.invoke.sample.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by peter.zuo on 2017/4/18.
 */
public class SampleInvocationHandler implements InvocationHandler {
    Object realObj;

    public SampleInvocationHandler(Object realObj) {
        this.realObj = realObj;
    }

    //proxy 对象基本没什么用
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(realObj, args);
    }
}
