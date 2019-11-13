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

    /**
     * 修改用户密码
     * @param password 输入没有加密的旧密码
     * @param newPwd 输入没有加密的新密码
     * @param cfgPwd 确认密码
     * @return
     */
    public int updateUserPwd(String password,String newPwd,String cfgPwd);


}
