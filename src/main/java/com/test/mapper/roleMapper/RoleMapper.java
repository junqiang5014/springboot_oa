package com.test.mapper.roleMapper;

import com.test.pojo.Role;

import java.util.List;

public interface RoleMapper {
    public int addRole(Role role);

    public int deleteRoleByRoleid(int roleid);

    public int updateRole(Role role);

    public Role getRoleByRoleid(int roleid);

    public List<Role> getRoleList();
}
