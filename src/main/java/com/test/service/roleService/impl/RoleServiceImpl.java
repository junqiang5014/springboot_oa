package com.test.service.roleService.impl;

import com.test.mapper.roleMapper.RoleMapper;
import com.test.pojo.Role;
import com.test.service.roleService.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public int addRole(Role role) {
        return roleMapper.addRole(role);
    }

    @Override
    public int deleteRoleByRoleid(int roleid) {
        return roleMapper.deleteRoleByRoleid(roleid);
    }

    @Override
    public int updateRole(Role role) {
        return roleMapper.updateRole(role);
    }

    @Override
    public Role getRoleByRoleid(int roleid) {
        return roleMapper.getRoleByRoleid(roleid);
    }

    @Override
    public List<Role> getRoleList() {
        return roleMapper.getRoleList();
    }
}
