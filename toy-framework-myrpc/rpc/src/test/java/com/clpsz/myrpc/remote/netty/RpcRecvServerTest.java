package com.clpsz.myrpc.remote.netty;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by pp.zuo on 2017/4/19.
 */
public class RpcRecvServerTest {
    @Test
    public void start() throws Exception {
        RpcRecvServer server = new RpcRecvServer();
        server.start();
    }
}