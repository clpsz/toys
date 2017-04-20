package com.clpsz.myrpc.remote.local;

import com.clpsz.myrpc.invoke.RpcInvoker;
import com.clpsz.myrpc.invoke.model.RequestMsg;
import com.clpsz.myrpc.invoke.model.ResponseMsg;
import com.clpsz.myrpc.invoke.proxy.ImplPool;
import com.clpsz.myrpc.util.InvokeUtil;

import java.lang.reflect.InvocationTargetException;


/**
 * Created by peter.zuo on 2017/4/19.
 */
public class LocalRpcInvoker implements RpcInvoker {
    @Override
    public ResponseMsg invoke(RequestMsg request) {
        ImplPool implPool = ImplPool.getInstance();
        try {
            return InvokeUtil.invokeMethod(implPool, request);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }
}
