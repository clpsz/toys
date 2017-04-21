package com.clpsz.rpc.spring.parser;

import com.clpsz.rpc.remote.netty.invoke.DynamicInvokeProxyFactory;
import org.springframework.beans.factory.FactoryBean;

/**
 * Created by pp.zuo on 2017/4/21.
 */
public class ClpszRpcReference implements FactoryBean {
    private String interfaceName;


    @Override
    public Object getObject() throws Exception {
        return DynamicInvokeProxyFactory.getProxy(getObjectType());
    }

    @Override
    public Class<?> getObjectType() {
        try {
            return this.getClass().getClassLoader().loadClass(interfaceName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }
}
