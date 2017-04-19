package com.clpsz.myrpc.client.sample.impl;

import com.clpsz.myrpc.client.sample.CalculatorService;
import com.clpsz.myrpc.invoke.proxy.ClientProxyFactory;
import com.clpsz.myrpc.invoke.sample.SampleAddService;

/**
 * Created by Administrator on 2017/4/18.
 */
public class CalculatorServiceImpl implements CalculatorService {
    @Override
    public int doAddOperation(String numA, String numB) {
        SampleAddService sampleAddService = ClientProxyFactory.getProxy(SampleAddService.class);
        return sampleAddService.add(Integer.valueOf(numA), Integer.valueOf(numB));
    }
}
