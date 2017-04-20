package com.clpsz.myrpc.invoke.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by peter.zuo on 2017/4/18.
 */
@Data
public class ResponseMsg implements Serializable {
    private String msgId;
    private Object result;
    private Throwable error;
}
