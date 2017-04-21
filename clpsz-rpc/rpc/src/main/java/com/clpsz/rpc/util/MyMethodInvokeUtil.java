package com.clpsz.rpc.util;

import com.clpsz.rpc.ServiceImplPool;
import com.clpsz.rpc.entity.RpcRequest;
import com.clpsz.rpc.entity.RpcResponse;
import org.apache.commons.lang3.reflect.MethodUtils;


/**
 * Created by pp.zuo on 2017/4/21.
 */
public class MyMethodInvokeUtil {
    public static RpcResponse invoke(RpcRequest request) {
        String interfaceName = request.getInterfaceName();
        String methodName = request.getMethodName();
        Class<?>[] parameterTypes = request.getParameterTypes();
        Object[] parameters = request.getParameters();

        Object bean = ServiceImplPool.getInstance().get(interfaceName);

        RpcResponse response = new RpcResponse();
        response.setId(request.getId());

        try {
            Object result = MethodUtils.invokeMethod(bean, methodName, parameters, parameterTypes);
            response.setResult(result);
        } catch (Throwable t) {
            t.printStackTrace();
            response.setCause(t);
        }

        return  response;
    }
}
