package com.exercise.service;

import java.lang.reflect.Proxy;

public class MyMapperTemplate extends MyMapperService {

    public <T> T getMapper(Class<T> type) {
        MyMapperProxy mapperProxy = new MyMapperProxy(this);
        return (T) Proxy.newProxyInstance(type.getClassLoader(), new Class[] { type }, mapperProxy);
    }
}
