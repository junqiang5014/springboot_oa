package com.test.service.studentService.impl;

import com.test.mapper.studentMapper.RoleMapper;
import com.test.pojo.Role;
import com.test.service.studentService.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    public RoleMapper getRoleMapper() {
        return roleMapper;
    }

    public void setRoleMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public Role getRole() {
        return roleMapper.getRole();
    }
}
