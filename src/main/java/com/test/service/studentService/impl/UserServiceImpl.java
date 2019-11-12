package com.test.service.studentService.impl;

import com.test.mapper.studentMapper.UserMapper;
import com.test.pojo.User;
import com.test.service.studentService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public int updateUpwdByUser(User user) {
        return userMapper.updateUpwdByUser(user);
    }

    @Override
    public String getUpwdByUname(String uname) {
        return userMapper.getUpwdByUname(uname);
    }

    @Override
    public String getRoleByUname(String uname) {
        return userMapper.getRoleByUname(uname);
    }

    @Override
    public List<User> getUserAndRole() {
        return userMapper.getUserAndRole();
    }
}
