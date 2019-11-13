package com.test.service.userService;

import com.test.mapper.userMapper.Role1Mapper;
import com.test.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Role1ServiceImpl implements Role1Service {

    @Autowired
    private Role1Mapper role1Mapper;
    @Override
    public int addRole(String rolename) {
        return role1Mapper.addRole(rolename);
    }

    @Override
    public int deleteRole(int roleid) {
        return role1Mapper.deleteRole(roleid);
    }

    @Override
    public int updateRole(int roleid) {
        return role1Mapper.updateRole(roleid);
    }

    @Override
    public List<Role> getRole() {
        return role1Mapper.getRole();
    }
}
