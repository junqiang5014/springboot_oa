package com.test.service.userService;

import com.test.pojo.User;

import java.util.List;

public interface UsersService {

    public User getUserByUname(String uname);

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
     * 根据角色修改密码
     * @param user
     * @return
     */
    public int updateUpwdByUser(User user);


    //根据uid来删除用户
    public int deleteUser(int uid);

    //根据用户名进行模糊搜索
    public List<User> getUserByLikeUname(String uname);

    //根据uid修改密码
    public int updatePassword(int uid);


}
