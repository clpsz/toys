package com.clpsz.myrpc.remote.thread;

import com.clpsz.myrpc.invoke.model.RequestMsg;
import com.clpsz.myrpc.invoke.model.ResponseMsg;
import com.clpsz.myrpc.invoke.proxy.ImplPool;
import com.clpsz.myrpc.remote.netty.MsgCallBack;
import com.clpsz.myrpc.util.InvokeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by pp.zuo on 2017/4/20.
 */
public class ResponseEndPoint {

    private Logger logger = LoggerFactory.getLogger(ResponseMsg.class);
    private ConcurrentHashMap<String, MsgCallBack> callBackMap = new ConcurrentHashMap<>();

    public void start() {
        Runnable task = () -> {
            LocalIoPipeline io = LocalIoPipeline.getInstance();
            ImplPool implPool = ImplPool.getInstance();
            while (true) {
                RequestMsg request = io.readRequest();
                logger.info("get request msg:{}", request);
                ResponseMsg response = InvokeUtil.invokeMethod(implPool, request);
                io.writeResponse(response);
                logger.info("write response msg:{}", response);
            }
        };

        new Thread(task).start();
    }
}
