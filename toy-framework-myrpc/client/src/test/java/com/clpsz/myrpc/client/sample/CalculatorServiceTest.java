package com.clpsz.myrpc.client.sample;

import com.clpsz.myrpc.client.sample.impl.CalculatorServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by peter.zuo on 2017/4/19.
 */
public class CalculatorServiceTest {
    @Test
    public void doAddOperation() throws Exception {
        CalculatorService calculatorService = new CalculatorServiceImpl();
        int result = calculatorService.doAddOperation("3", "40");
        System.out.println(result);
    }
}