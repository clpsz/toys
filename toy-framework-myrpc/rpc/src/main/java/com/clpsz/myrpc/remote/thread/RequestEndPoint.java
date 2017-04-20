package com.clpsz.myrpc.remote.thread;

import com.clpsz.myrpc.invoke.model.RequestMsg;
import com.clpsz.myrpc.invoke.model.ResponseMsg;
import com.clpsz.myrpc.remote.netty.MsgCallBack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by pp.zuo on 2017/4/20.
 */
public class RequestEndPoint {

    private Logger logger = LoggerFactory.getLogger(RequestEndPoint.class);
    private ConcurrentHashMap<String, MsgCallBack> callBackMap = new ConcurrentHashMap<>();
    private static RequestEndPoint INSTANCE = new RequestEndPoint();

    private RequestEndPoint() {}

    public static RequestEndPoint getInstance() {
        return INSTANCE;
    }

    public MsgCallBack sendRequest(RequestMsg request) {
        LocalIoPipeline io = LocalIoPipeline.getInstance();
        String msgId = request.getMsgId();

        MsgCallBack callBack = new MsgCallBack();
        callBackMap.put(msgId, callBack);
        io.writeRequest(request);
        logger.info("write request msg:{}", request);

        return callBack;
    }

    public void start() {
        Runnable task = () -> {
            LocalIoPipeline io = LocalIoPipeline.getInstance();
            while (true) {
                ResponseMsg response = io.readResponse();
                logger.info("get response msg:{}", response);
                String msgId = response.getMsgId();
                MsgCallBack callBack = callBackMap.get(msgId);
                if (callBack != null) {
                    callBackMap.remove(msgId);
                    callBack.set(response.getResult());
                }
            }
        };

        new Thread(task).start();
    }
}
