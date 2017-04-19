package com.clpsz.myrpc.remote.netty;

import com.clpsz.myrpc.invoke.model.RequestMsg;
import com.clpsz.myrpc.invoke.model.ResponseMsg;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * Created by pp.zuo on 2017/4/19.
 */
public class NettyInvocationHandler implements InvocationHandler{
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        ChannelSendHandler handler = SendHandlerHolder.getInstance().getSendHandler();

        RequestMsg request = new RequestMsg();
        request.setMsgId(UUID.randomUUID().toString());
        request.setClassName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setMethodParameterTypes(method.getParameterTypes());
        request.setParameters(args);

        MsgCallBack callBack = handler.sendRequest(request);
        ResponseMsg responseMsg = (ResponseMsg) callBack.get();

        return responseMsg.getResult();
    }
}
