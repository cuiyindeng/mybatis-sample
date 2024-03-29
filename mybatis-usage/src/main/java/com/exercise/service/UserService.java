package com.exercise.service;

import com.exercise.mapper.UserJpaDao;
import com.exercise.mapper.UserMapper;
import com.exercise.model.User;
import com.exercise.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserJpaDao userJpaDao;

    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    };

    @Transactional
    public int addUser(User user) {
//        getAllUser();
        return userMapper.addUser(user);
    }

    public List<Users> findAll() {
//        return userMapper.findAll();
        return userJpaDao.findAll();
    };
}
