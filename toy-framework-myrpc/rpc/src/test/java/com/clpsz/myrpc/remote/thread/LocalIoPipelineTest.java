package com.clpsz.myrpc.remote.thread;

import com.clpsz.myrpc.invoke.model.RequestMsg;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sun.java2d.SurfaceDataProxy;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Created by pp.zuo on 2017/4/20.
 */
public class LocalIoPipelineTest {
    private LocalIoPipeline instance;

    @Before
    public void setUp() throws Exception {
        instance = LocalIoPipeline.getInstance();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getInstance() throws Exception {
        System.out.println(instance);
    }


    @Test
    public void writeRequest()  {
        CountDownLatch allThread = new CountDownLatch(2);
        CountDownLatch readStarted = new CountDownLatch(1);
        CountDownLatch writeStarted = new CountDownLatch(1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestMsg request = new RequestMsg();
                request.setMsgId(UUID.randomUUID().toString());
                instance.writeRequest(request);
                readStarted.countDown();
                allThread.countDown();
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestMsg request = instance.readRequest();
                System.out.println(request);
                writeStarted.countDown();
                allThread.countDown();
            }
        }).start();

        try {
            allThread.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}