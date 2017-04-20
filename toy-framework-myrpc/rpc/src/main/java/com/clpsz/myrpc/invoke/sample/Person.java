package com.clpsz.myrpc.invoke.sample;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by pp.zuo on 2017/4/20.
 */
@Data
public class Person implements Serializable {
    private String name;
    private int age;
    private SEX sex;
    private Book favoriteBook;


    public static enum SEX {MALE, FEMALE}

}
