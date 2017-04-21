package com.clpsz.rpc.remote.netty.callee;


import com.clpsz.rpc.example.calculator.CalculateService;
import com.clpsz.rpc.remote.netty.invoke.DynamicInvokeProxyFactory;
import com.clpsz.rpc.remote.netty.caller.CallerConnector;
import org.junit.Test;

/**
 * Created by pp.zuo on 2017/4/21.
 */
public class CalleeServerLoaderTest {
    @Test
    public void startCallee() throws Exception {
        CalleeServerLoader callee = new CalleeServerLoader();
        callee.start();
    }

    @Test
    public void startCaller() throws Exception {
        CallerConnector caller = new CallerConnector();
        caller.start();
        CalculateService calculateService = DynamicInvokeProxyFactory.getProxy(CalculateService.class);
        int result = calculateService.add(3, 4);
        System.out.println("===================" + result);
    }
}