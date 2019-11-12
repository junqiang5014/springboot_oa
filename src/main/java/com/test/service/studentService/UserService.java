package com.test.service.studentService;

import com.test.pojo.User;

import java.util.List;

public interface UserService {

    /**
     * 根据角色修改密码
     * @param user
     * @return
     */
    public int updateUpwdByUser(User user);

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
