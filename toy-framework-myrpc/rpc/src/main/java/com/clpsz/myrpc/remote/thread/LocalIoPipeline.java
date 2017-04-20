package com.clpsz.myrpc.remote.thread;

import com.clpsz.myrpc.invoke.model.RequestMsg;
import com.clpsz.myrpc.invoke.model.ResponseMsg;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by pp.zuo on 2017/4/20.
 */
public class LocalIoPipeline {
    private static LocalIoPipeline INSTANCE = new LocalIoPipeline();

    private final BlockingQueue<RequestMsg> requestMsgs = new LinkedBlockingQueue<>();
    private final BlockingQueue<ResponseMsg> responseMsgs = new LinkedBlockingQueue<>();

    private LocalIoPipeline() {}


    public static LocalIoPipeline getInstance() {
        return INSTANCE;
    }

    public void writeRequest(RequestMsg request) {
        try {
            requestMsgs.put(request);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public RequestMsg readRequest() {
        try {
            return requestMsgs.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void writeResponse(ResponseMsg response) {
        try {
            responseMsgs.put(response);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public ResponseMsg readResponse() {
        try {
            return responseMsgs.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }
}
