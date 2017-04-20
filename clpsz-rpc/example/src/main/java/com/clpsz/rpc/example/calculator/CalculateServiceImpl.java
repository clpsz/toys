package com.clpsz.rpc.example.calculator;

/**
 * Created by pp.zuo on 2017/4/20.
 */
public class CalculateServiceImpl implements CalculateService {
    @Override
    public int add(int a, int b) {
        return a + b;
    }
}
