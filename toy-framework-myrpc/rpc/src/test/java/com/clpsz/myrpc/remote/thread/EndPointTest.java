package com.clpsz.myrpc.remote.thread;


import com.clpsz.myrpc.invoke.proxy.ClientProxyFactory;
import com.clpsz.myrpc.invoke.sample.*;
import org.junit.Before;
import org.junit.Test;



/**
 * Created by pp.zuo on 2017/4/20.
 */
public class EndPointTest {
    LocalIoPipeline instance;


    @Before
    public void setUp() throws Exception {
        instance = LocalIoPipeline.getInstance();
    }

    @Test
    public void testSampleAdd() throws Exception {
        new ResponseEndPoint().start();
        RequestEndPoint.getInstance().start();

        SampleAddService sampleAddService = ClientProxyFactory.getThreadProxy(SampleAddService.class);
        int result = sampleAddService.add(333, 4000);

        System.out.println(result);
    }

    @Test
    public void testSampleSayHello() throws Exception {
        new ResponseEndPoint().start();
        RequestEndPoint.getInstance().start();

        SampleSayHelloService sampleSayHelloService = ClientProxyFactory.getThreadProxy(SampleSayHelloService.class);
        String result = sampleSayHelloService.sayHello("himmie");
        String result1 = sampleSayHelloService.sayHello("peter", "hemmie");

        System.out.println(result);
        System.out.println(result1);
    }

    @Test
    public void testFindGirlFriend() throws Exception {
        new ResponseEndPoint().start();
        RequestEndPoint.getInstance().start();

        FindGirlFriendService findGirlFriendService = ClientProxyFactory.getThreadProxy(FindGirlFriendService.class);
        Person boy = new Person();
        boy.setName("Perter Zuo");
        boy.setSex(Person.SEX.MALE);
        boy.setAge(28);
        Book book = new Book();
        book.setTitle("Moby Dic1");
        book.setAuthor("I don't know");
        boy.setFavoriteBook(book);

        Person girl = findGirlFriendService.findGirlFriend(boy);
        System.out.println("get response");
        System.out.println(girl);
    }
}