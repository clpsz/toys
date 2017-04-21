package com.clpsz.rpc.remote.netty.invoke;

import com.clpsz.rpc.entity.RpcFuture;
import com.clpsz.rpc.entity.RpcRequest;
import com.clpsz.rpc.entity.RpcResponse;
import com.clpsz.rpc.remote.netty.caller.RpcRequestProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * Created by pp.zuo on 2017/4/20.
 */
public class DynamicInvokeHandler implements InvocationHandler{
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest request = new RpcRequest();
        request.setId(UUID.randomUUID().toString());
        request.setInterfaceName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setParameterTypes(method.getParameterTypes());
        request.setParameters(args);

        RpcFuture future = RpcRequestProxy.getInstance().sendRequest(request);
        RpcResponse response = future.get();
        if (response.getCause() != null) {
            throw response.getCause();
        }

        return response.getResult();
    }
}
