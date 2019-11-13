package com.test.service.userService1.impl;

import com.test.mapper.userMapper1.UserMapper1;
import com.test.pojo.User;
import com.test.service.userService1.UserService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl1 implements UserService1 {

    @Autowired
    private UserMapper1 userMapper1;

    @Override
    public int updateUser(User user) {
        return userMapper1.updateUser(user);
    }
}
