package com.clpsz.rpc.example.bootstrap;

import com.clpsz.rpc.ServiceImplPool;
import com.clpsz.rpc.example.calculator.CalculateService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by pp.zuo on 2017/4/20.
 */
public class ServerBootstrap {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:clpszrpc-server.xml");
        ServiceImplPool instance = ServiceImplPool.getInstance();

        System.out.println(instance.get("com.clpsz.rpc.example.calculator.CalculateService"));
        System.out.println(ctx.getBean("calculateServiceImpl", CalculateService.class));
    }
}
