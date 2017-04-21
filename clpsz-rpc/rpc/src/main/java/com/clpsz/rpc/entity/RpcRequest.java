package com.clpsz.rpc.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by pp.zuo on 2017/4/21.
 */
@Data
public class RpcRequest implements Serializable {
    String id;
    String interfaceName;
    String methodName;
    Class<?>[] parameterTypes;
    Object[] parameters;
}
