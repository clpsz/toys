package com.clpsz.rpc.spring.parser;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created by pp.zuo on 2017/4/20.
 */
public class ClpszRpcNamespaceHandler extends NamespaceHandlerSupport{
    @Override
    public void init() {
        registerBeanDefinitionParser("service", new ClpszRpcServiceParser());
    }
}
