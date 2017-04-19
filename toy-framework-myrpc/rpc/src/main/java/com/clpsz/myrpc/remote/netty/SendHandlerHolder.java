package com.clpsz.myrpc.remote.netty;


/**
 * Created by pp.zuo on 2017/4/19.
 */
public class SendHandlerHolder {
    private final static SendHandlerHolder INSTANCE = new SendHandlerHolder();

    private ChannelSendHandler handler = null;

    private SendHandlerHolder() {}


    public static SendHandlerHolder getInstance() {
        return INSTANCE;
    }

    public ChannelSendHandler getSendHandler() {
        return handler;
    }

    public void setHandler(ChannelSendHandler handler) {
        this.handler = handler;
    }
}
