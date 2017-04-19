package com.clpsz.myrpc.invoke.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by peter.zuo on 2017/4/18.
 */
@Data
public class RequestMsg implements Serializable {
    private String msgId;
    private String className;
    private String methodName;
    private Class<?>[] methodParameterTypes;
    private Object[] parameters;
}
