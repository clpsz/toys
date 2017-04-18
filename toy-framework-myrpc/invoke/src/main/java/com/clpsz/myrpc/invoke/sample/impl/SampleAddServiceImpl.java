package com.clpsz.myrpc.invoke.sample.impl;

import com.clpsz.myrpc.invoke.sample.SampleAddService;


/**
 * Created by peter.zuo on 2017/4/18.
 */
public class SampleAddServiceImpl implements SampleAddService {
    @Override
    public int add(int a, int b) {
        return a + b;
    }
}
