package com.clpsz.rpc.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by pp.zuo on 2017/4/21.
 */
@Data
public class RpcResponse implements Serializable {
    String id;
    Object result;
    Throwable cause;
}
