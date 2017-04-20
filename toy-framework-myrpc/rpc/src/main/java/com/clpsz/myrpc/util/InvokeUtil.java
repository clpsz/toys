package com.clpsz.myrpc.util;

import com.clpsz.myrpc.invoke.model.RequestMsg;
import com.clpsz.myrpc.invoke.model.ResponseMsg;
import com.clpsz.myrpc.invoke.proxy.ImplPool;
import org.apache.commons.lang3.reflect.MethodUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by pp.zuo on 2017/4/19.
 */
public class InvokeUtil {
    public static ResponseMsg invokeMethod(ImplPool implPool, RequestMsg request)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object bean = implPool.getImpl(request.getClassName());
        String methodName = request.getMethodName();
        Object[] parameters = request.getParameters();

        Object result = MethodUtils.invokeMethod(bean, methodName, parameters);


        ResponseMsg responseMsg = new ResponseMsg();
        responseMsg.setMsgId(request.getMsgId());
        responseMsg.setResult(result);

        return responseMsg;
    }
}
