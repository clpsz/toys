package com.clpsz.rpc.example.bootstrap;

import com.clpsz.rpc.remote.netty.callee.CalleeServerLoader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Created by pp.zuo on 2017/4/20.
 */
public class ServerBootstrap {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:clpszrpc-server.xml");

        CalleeServerLoader calleeServerLoader = ctx.getBean("calleeServerLoader", CalleeServerLoader.class);
        calleeServerLoader.start();
    }
}
