package com.clpsz.myrpc.remote.thread;

import com.clpsz.myrpc.invoke.model.RequestMsg;
import com.clpsz.myrpc.invoke.model.ResponseMsg;
import com.clpsz.myrpc.remote.netty.ChannelSendHandler;
import com.clpsz.myrpc.remote.netty.MsgCallBack;
import com.clpsz.myrpc.remote.netty.SendHandlerHolder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * Created by pp.zuo on 2017/4/20.
 */
public class ThreadInvocationHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RequestEndPoint requestEndPoint = RequestEndPoint.getInstance();

        RequestMsg request = new RequestMsg();
        request.setMsgId(UUID.randomUUID().toString());
        request.setClassName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setMethodParameterTypes(method.getParameterTypes());
        request.setParameters(args);

        MsgCallBack callBack = requestEndPoint.sendRequest(request);
        ResponseMsg response = (ResponseMsg) callBack.get();
        if (response.getError() != null) {
            throw response.getError();
        }

        return response.getResult();
    }
}
