package com.exercise.service;

import org.springframework.beans.factory.FactoryBean;

/**
 * 被spring托管的Bean。
 */
public class MyMapperFactoryBean<T> implements FactoryBean<T> {
    private Class<T> mapperInterface;
    private MyMapperTemplate myMapperTemplate;

    public MyMapperFactoryBean() {
        //intentionally empty
    }

    //用constructor argument values注入
    public MyMapperFactoryBean(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    //用set方法注入
    public void setMyMapperTemplate(MyMapperTemplate myMapperTemplate) {
        this.myMapperTemplate = myMapperTemplate;
    }

    @Override
    public T getObject() throws Exception {
        return myMapperTemplate.getMapper(mapperInterface);
    }

    @Override
    public Class<?> getObjectType() {
        return mapperInterface;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
