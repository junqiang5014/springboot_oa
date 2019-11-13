package com.test.mapper.userMapper;

import com.test.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UsersMapper {

    public User getUserByUname(String uname);//aa

    /**
     * 修改密码
     * @param uname
     * @param upwd
     * @return
     */
    public int updateUpwdByUser(@Param("uname") String uname,@Param("upwd") String upwd);

    /**
     * 根据姓名查询密码
     * @param uname
     * @return
     */
    public String getUpwdByUname(String uname);

    /**
     * 根据姓名查询角色
     * @param uname
     * @return
     */
    public String getRoleByUname(String uname);

    /**
     * 查询user
     * @return
     */
    public List<User> getUserAndRole();


}
