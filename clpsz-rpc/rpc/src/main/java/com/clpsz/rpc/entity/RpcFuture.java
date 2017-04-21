package com.clpsz.rpc.entity;

/**
 * Created by pp.zuo on 2017/4/21.
 */
public class RpcFuture {
    private RpcResponse response = null;

    public RpcResponse get() throws InterruptedException{
        synchronized (this) {
            while (response == null) {
                wait();
            }

            return response;
        }
    }

    public void set(RpcResponse response) {
        this.response = response;
        synchronized (this) {
            notifyAll();
        }
    }
}
