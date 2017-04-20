package com.clpsz.myrpc.invoke.sample.impl;

import com.clpsz.myrpc.invoke.sample.Book;
import com.clpsz.myrpc.invoke.sample.FindGirlFriendService;
import com.clpsz.myrpc.invoke.sample.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by pp.zuo on 2017/4/20.
 */
public class FindGirlFriendServiceImpl implements FindGirlFriendService {
    Logger logger = LoggerFactory.getLogger(FindGirlFriendServiceImpl.class);


    @Override
    public Person findGirlFriend(Person boy) {
        if (boy.getSex() == Person.SEX.FEMALE) {
            logger.warn("you don't need a girl friend!");
            return null;
        }

        Book favoriteBook = boy.getFavoriteBook();
        if (!favoriteBook.getTitle().equals("Moby Dic")) {
            logger.warn("you don't event like Moby Dic, you should not have a girl friend");
            return null;
        }

        Person girl = new Person();
        girl.setName("Jane");
        girl.setSex(Person.SEX.FEMALE);
        girl.setAge(boy.getAge() - 3);

        Book girlBook = new Book();
        girlBook.setAuthor("Xueqin Cao");
        girlBook.setTitle("红楼梦");
        girlBook.setPublishDate(new Date());

        girl.setFavoriteBook(girlBook);
        return girl;
    }
}
