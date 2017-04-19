package com.clpsz.myrpc.remote.local;

import com.clpsz.myrpc.invoke.RpcInvoker;
import com.clpsz.myrpc.invoke.model.RequestMsg;
import com.clpsz.myrpc.invoke.model.ResponseMsg;
import com.clpsz.myrpc.invoke.proxy.ImplPool;
import com.clpsz.myrpc.util.InvokeUtil;


/**
 * Created by peter.zuo on 2017/4/19.
 */
public class LocalRpcInvoker implements RpcInvoker {
    @Override
    public ResponseMsg invoke(RequestMsg request) {
        ImplPool implPool = ImplPool.getInstance();
        return InvokeUtil.invokeMethod(implPool, request);
    }
}
