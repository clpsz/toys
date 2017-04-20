package com.clpsz.myrpc.invoke.sample;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by pp.zuo on 2017/4/20.
 */
@Data
public class Book implements Serializable {
    private String title;
    private String author;
    private Date publishDate;
}
