package com.clpsz.myrpc.invoke.proxy;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by peter.zuo on 2017/4/18.
 */
public class ImplPool {
    private Map<String, Object> implMap = new HashMap<>();

    private static ImplPool INSTANCE = new ImplPool();

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
