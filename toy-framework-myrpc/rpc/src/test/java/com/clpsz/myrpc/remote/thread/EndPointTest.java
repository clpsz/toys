package com.clpsz.myrpc.remote.thread;

import com.clpsz.myrpc.invoke.model.RequestMsg;
import com.clpsz.myrpc.invoke.proxy.ClientProxyFactory;
import com.clpsz.myrpc.invoke.sample.SampleAddService;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Created by pp.zuo on 2017/4/20.
 */
public class EndPointTest {
    LocalIoPipeline instance;


    @Before
    public void setUp() throws Exception {
        instance = LocalIoPipeline.getInstance();
    }

    @Test
    public void all() throws Exception {
        new ResponseEndPoint().start();
        RequestEndPoint.getInstance().start();

        SampleAddService sampleAddService = ClientProxyFactory.getThreadProxy(SampleAddService.class);
        int result = sampleAddService.add(333, 4000);

        System.out.println(result);
    }
}