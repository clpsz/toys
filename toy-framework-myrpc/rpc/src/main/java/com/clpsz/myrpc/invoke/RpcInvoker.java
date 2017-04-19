package com.clpsz.myrpc.invoke;

import com.clpsz.myrpc.invoke.model.RequestMsg;
import com.clpsz.myrpc.invoke.model.ResponseMsg;


/**
 * Created by peter.zuo on 2017/4/19.
 */
public interface RpcInvoker {
    ResponseMsg invoke(RequestMsg request);
}
