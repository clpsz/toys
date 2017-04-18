package com.clpsz.myrpc.invoke.sample;

import com.clpsz.myrpc.invoke.sample.impl.SampleAddServiceImpl;
import com.clpsz.myrpc.invoke.sample.impl.SampleInvocationHandler;
import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * Created by peter.zuo on 2017/4/18.
 */
public class SampleAddServiceTest {
    @Test
    public void testAddByProxy() {
        SampleAddService realObj = new SampleAddServiceImpl();
        SampleAddService proxy = (SampleAddService) Proxy.newProxyInstance(SampleAddService.class.getClassLoader(),
                new Class[]{SampleAddService.class}, new SampleInvocationHandler(realObj));
        int result = proxy.add(3, 5);
        System.out.println(result);
    }
}
