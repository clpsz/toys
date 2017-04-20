package com.clpsz.myrpc.invoke.sample;

/**
 * Created by peter.zuo on 2017/4/18.
 */
public interface SampleSayHelloService {
    String sayHello(String word);
    String sayHello(String prefix, String word);
}
