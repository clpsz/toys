package com.clpsz.myrpc.remote.netty;

/**
 * Created by pp.zuo on 2017/4/19.
 */
//TODO: 改成类型安全的
public class MsgCallBack {
    private Object result = null;

    public Object get() throws InterruptedException {
        if (result == null) {
            synchronized (this) {
                while (result == null) {
                    wait();
                }
            }
        }

        return result;
    }

    public void set(Object result) {
        synchronized (this) {
            this.result = result;
            notifyAll();
        }
    }
}
