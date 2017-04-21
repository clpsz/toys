package com.clpsz.rpc.example.bootstrap;

import com.clpsz.rpc.example.calculator.CalculateService;
import com.clpsz.rpc.remote.netty.caller.CallerConnector;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by pp.zuo on 2017/4/21.
 */
public class ClientBootstrap {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:clpszrpc-client.xml");
        new CallerConnector().start();
        CalculateService calculateService = ctx.getBean("calculateService", CalculateService.class);
        int result = calculateService.add(3, 40000);
        System.out.println(result);
    }
}
