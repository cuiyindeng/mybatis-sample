package com.exercise.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyMapperProxy  implements InvocationHandler {

    MyMapperService myMapperService;

    public MyMapperProxy(MyMapperService myMapperService) {
        this.myMapperService = myMapperService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("模拟代理方法执行");
        return myMapperService.select();
    }
}
