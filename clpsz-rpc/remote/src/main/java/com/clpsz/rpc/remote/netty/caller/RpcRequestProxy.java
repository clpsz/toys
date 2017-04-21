package com.clpsz.rpc.remote.netty.caller;

import com.clpsz.rpc.entity.RpcFuture;
import com.clpsz.rpc.entity.RpcRequest;
import com.clpsz.rpc.entity.RpcResponse;
import io.netty.channel.Channel;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by pp.zuo on 2017/4/21.
 */
public class RpcRequestProxy {
    private static RpcRequestProxy INSTANCE = new RpcRequestProxy();
    private Channel channel;
    private ConcurrentHashMap<String, RpcFuture> futureMap = new ConcurrentHashMap<>();


    private RpcRequestProxy() {}

    public static RpcRequestProxy getInstance() {
        return INSTANCE;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public void processResponse(RpcResponse response) {
        String id = response.getId();
        RpcFuture future = futureMap.get(id);
        if (future != null) {
            futureMap.remove(id);
            future.set(response);
        }
    }

    public RpcFuture sendRequest(RpcRequest request) {
        RpcFuture future = new RpcFuture();
        try {
            channel.writeAndFlush(request).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        futureMap.put(request.getId(), future);

        return future;
    }

    public void closeChannel() {
        channel.close();
    }
}
