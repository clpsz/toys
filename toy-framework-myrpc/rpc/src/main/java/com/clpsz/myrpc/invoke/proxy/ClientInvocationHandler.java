package com.clpsz.myrpc.invoke.proxy;

import com.clpsz.myrpc.invoke.RpcInvoker;
import com.clpsz.myrpc.invoke.model.RequestMsg;
import com.clpsz.myrpc.invoke.model.ResponseMsg;
import com.clpsz.myrpc.remote.local.LocalRpcInvoker;
import org.apache.commons.lang3.reflect.MethodUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by peter.zuo on 2017/4/18.
 */
public class ClientInvocationHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RequestMsg request = new RequestMsg();

        request.setClassName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setMethodParameterTypes(method.getParameterTypes());
        request.setParameters(args);

        RpcInvoker invoker = new LocalRpcInvoker();
        ResponseMsg response = invoker.invoke(request);

        return response.getResult();
    }
}
