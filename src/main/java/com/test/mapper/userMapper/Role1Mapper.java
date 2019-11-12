package com.test.mapper.userMapper;

import com.test.pojo.Role;

import java.util.List;

public interface Role1Mapper {

    /**
     * 增加角色
     */
    public int addRole(String rolename);

    /**
     * 删除角色
     * @param roleid
     * @return
     */
    public int deleteRole(int roleid);

    /**
     * 修改角色名称
     * @param roleid
     * @return
     */
    public int updateRole(int roleid);

    /**
     * 获取所有的角色名
     * @return
     */
    public List<Role> getRole();
}
