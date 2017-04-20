package com.clpsz.rpc;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by pp.zuo on 2017/4/20.
 */
public class ServiceImplPool {
    private static ServiceImplPool INSTANCE = new ServiceImplPool();
    private Map<String, Object> handlerMap = new HashMap<>();

    private ServiceImplPool() {}

    public static ServiceImplPool getInstance() {
        return INSTANCE;
    }

    public void put(String interfaceName, Object bean) {
        handlerMap.put(interfaceName, bean);
    }

    public Object get(String interfaceName) {
        return handlerMap.get(interfaceName);
    }
}
