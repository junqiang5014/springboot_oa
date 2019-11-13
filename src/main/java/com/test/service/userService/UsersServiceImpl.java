package com.test.service.userService;

import com.test.mapper.userMapper.UsersMapper;
import com.test.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersMapper usersMapper;
    @Override
    public User getUserByUname(String uname) {
        return usersMapper.getUserByUname(uname);
    }

    @Override
    public String getUpwdByUname(String uname) {
        return usersMapper.getUpwdByUname(uname);
    }

    @Override
    public String getRoleByUname(String uname) {
        return usersMapper.getRoleByUname(uname);
    }

    @Override
    public List<User> getUserAndRole() {
        return usersMapper.getUserAndRole();
    }

    @Override
    public int updateUpwdByUser(User user) {
        return usersMapper.updateUpwdByUser(user);
    }

    @Override
    public int deleteUser(int uid) {
        return usersMapper.deleteUser(uid);
    }

    @Override
    public List<User> getUserByLikeUname(String uname) {
        return usersMapper.getUserByLikeUname(uname);
    }

    @Override
    public int updatePassword(int uid) {
        return usersMapper.updatePassword(uid);
    }

}
