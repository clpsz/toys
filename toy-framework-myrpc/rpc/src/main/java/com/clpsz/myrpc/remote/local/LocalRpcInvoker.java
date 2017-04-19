package com.clpsz.myrpc.remote.local;

import com.clpsz.myrpc.invoke.RpcInvoker;
import com.clpsz.myrpc.invoke.model.RequestMsg;
import com.clpsz.myrpc.invoke.model.ResponseMsg;
import com.clpsz.myrpc.invoke.proxy.ImplPool;
import org.apache.commons.lang3.reflect.MethodUtils;

import java.lang.reflect.InvocationTargetException;


/**
 * Created by peter.zuo on 2017/4/19.
 */
public class LocalRpcInvoker implements RpcInvoker {
    @Override
    public ResponseMsg invoke(RequestMsg request) {
        ImplPool implPool = ImplPool.getInstance();

        Object bean = implPool.getImpl(request.getClassName());
        String methodName = request.getMethodName();
        Object[] parameters = request.getParameters();

        Object result = null;
        try {
            result = MethodUtils.invokeMethod(bean, methodName, parameters);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        ResponseMsg responseMsg = new ResponseMsg();
        responseMsg.setResult(result);

        return responseMsg;
    }
}
