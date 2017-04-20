package com.clpsz.myrpc.invoke.sample.impl;

import com.clpsz.myrpc.invoke.sample.SampleSayHelloService;

/**
 * Created by pp.zuo on 2017/4/20.
 */
public class SampleSayHelloServiceImpl implements SampleSayHelloService {
    @Override
    public String sayHello(String word) {
        return "Hello " + word;
    }
}
