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


    /**
     * 根据uid删除用户
     */
    public int deleteUser(int uid);

    /**
     * 根据用户名进行模糊搜索
     */
    public List<User> getUserByLikeUname(String uname);

    /**
     * 修改密码
     */
    public int updatePassword(User user);


    /**
     * 添加员工信息后自动添加账户,该uid和相应员工对应
     */
    public int addUser(User user);



}
