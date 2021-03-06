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
     * @param upwd 输入没有加密的旧密码
     * @param newPwd 输入没有加密的新密码
     * @param cfgPwd 确认密码
     * @return
     */
    public int updateUserPwd(String upwd,String newPwd,String cfgPwd);


    //根据uid来删除用户,并且删除员工信息
    public int deleteUser(int uid);

    //根据用户名进行模糊搜索
    public List<User> getUserByLikeUname(String uname);

    //根据uid修改密码
    public int updatePassword(User user);

    //增加用户
    public int addUser(User user);


    /**
     * 根据用户id查询角色id
     */

    public int getRoleById(int uid);

    /**
     * 根据角色id查询角色姓名
     */

    public String getRoleNameByRoleId(int roleid);


}
