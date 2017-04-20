package com.clpsz.myrpc.invoke.proxy;


import com.clpsz.myrpc.invoke.sample.impl.FindGirlFriendServiceImpl;
import com.clpsz.myrpc.invoke.sample.impl.SampleAddServiceImpl;
import com.clpsz.myrpc.invoke.sample.impl.SampleSayHelloServiceImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by peter.zuo on 2017/4/18.
 */
public class ImplPool {
    private Map<String, Object> implMap = new HashMap<>();

    private static ImplPool INSTANCE = new ImplPool();

    static {
        //TODO: 改成用SampleAddService.class作为key
        INSTANCE.add("com.clpsz.myrpc.invoke.sample.SampleAddService", new SampleAddServiceImpl());
        INSTANCE.add("com.clpsz.myrpc.invoke.sample.SampleSayHelloService", new SampleSayHelloServiceImpl());
        INSTANCE.add("com.clpsz.myrpc.invoke.sample.FindGirlFriendService", new FindGirlFriendServiceImpl());
    }

    private ImplPool() {}

    public void add(String interfaze, Object impl) {
        implMap.put(interfaze, impl);
    }

    public Object getImpl(String interfaze) {
        if (interfaze == null) {
            throw new NullPointerException("接口名为空");
        }
        if (!implMap.containsKey(interfaze)) {
            throw new IllegalArgumentException("该接口无对应的实现类: " + interfaze);
        }

        return implMap.get(interfaze);
    }

    public static ImplPool getInstance() {
        return INSTANCE;
    }
}
