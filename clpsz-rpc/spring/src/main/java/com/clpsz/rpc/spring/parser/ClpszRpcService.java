package com.clpsz.rpc.spring.parser;

import com.clpsz.rpc.ServiceImplPool;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * Created by pp.zuo on 2017/4/20.
 */
public class ClpszRpcService implements ApplicationContextAware, ApplicationListener{
    private String interfaceName;
    private String ref;
    private ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        ServiceImplPool.getInstance().put(interfaceName, ctx.getBean(ref));
    }


    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
}
