package com.test.service.userService;

import com.test.mapper.userMapper.UsersMapper;
import com.test.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;

public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersMapper usersMapper;
    @Override
    public User getUserByUname(String uname) {
        return usersMapper.getUserByUname(uname);
    }
}
