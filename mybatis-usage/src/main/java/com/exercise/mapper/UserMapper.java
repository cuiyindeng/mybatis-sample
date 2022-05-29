package com.exercise.mapper;

import com.exercise.model.User;
import com.exercise.model.Users;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user")
    List<User> getAllUser();

    @Insert("insert into user (name, age) value(#{name}, #{age})")
    int addUser(User user);

    //xml方式
    List<Users> findAll();
}
