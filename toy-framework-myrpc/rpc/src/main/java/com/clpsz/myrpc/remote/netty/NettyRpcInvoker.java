package com.clpsz.myrpc.remote.netty;

import com.clpsz.myrpc.invoke.RpcInvoker;
import com.clpsz.myrpc.invoke.model.RequestMsg;
import com.clpsz.myrpc.invoke.model.ResponseMsg;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by pp.zuo on 2017/4/19.
 */
public class NettyRpcInvoker implements RpcInvoker {
    private final ConcurrentHashMap<String, MsgCallBack> callBackMap = new ConcurrentHashMap<>();

    @Override
    public ResponseMsg invoke(RequestMsg request) {
        MsgCallBack callBack = new MsgCallBack();
        callBackMap.put(request.getMsgId(), callBack);

        Object response = null;
        try {
            response = callBack.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ResponseMsg result = new ResponseMsg();
        result.setMsgId(request.getMsgId());
        result.setResult(response);

        return result;
    }
}
