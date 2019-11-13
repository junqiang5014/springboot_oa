package com.test.service.userService;

import com.test.mapper.userMapper.UsersMapper;
import com.test.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersMapper usersMapper;
    @Override
    public User getUserByUname(String uname) {
        return usersMapper.getUserByUname(uname);
    }

    @Override
    public String getUpwdByUname(String uname) {
        return usersMapper.getUpwdByUname(uname);
    }

    @Override
    public String getRoleByUname(String uname) {
        return usersMapper.getRoleByUname(uname);
    }

    @Override
    public List<User> getUserAndRole() {
        return usersMapper.getUserAndRole();
    }

    @Override
    public int updateUpwdByUser(User user) {
        return 0;
    }


    @Override
    public int updateUserPwd(String upwd, String newPwd, String cfgPwd) {
        //对参数进行非空验证
        if(upwd==null||upwd.length()==0){
            throw new IllegalArgumentException("请输入旧密码！");
        }
        if(newPwd==null||newPwd.length()==0){
            throw new IllegalArgumentException("请输入新密码！");
        }
        if(cfgPwd==null||cfgPwd.length()==0){
            throw new IllegalArgumentException("请确认新密码！");
        }
        //验证参数的合法性
        //判断两次输入的新密码是否相同
        if(!newPwd.equals(cfgPwd)){
            throw new IllegalArgumentException("两次输入的密码不一致！");
        }
        //判断旧密码跟登录密码是否一致
        //先获取用户的登陆身份通过shiro认证管理工具获得
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Md5Hash md5Hash=new Md5Hash(upwd);
        //sh.toHex()是将加密后的密码转换为16进制
        if(!user.getUpwd().equals(md5Hash.toHex())){
            throw new IllegalArgumentException("输入的旧密码不正确！");
        }
        //判断新旧密码是否一致
        if(upwd.equals(newPwd)){
            throw new IllegalArgumentException("新密码不能与旧密码相同！");
        }
        //更新密码
        md5Hash=new Md5Hash(newPwd);
        int i = usersMapper.updateUpwdByUser(md5Hash.toHex(), user.getUname());

        return i;
    }

    @Override
    public int deleteUser(int uid) {
        return usersMapper.deleteUser(uid);
    }

    @Override
    public List<User> getUserByLikeUname(String uname) {
        return usersMapper.getUserByLikeUname(uname);
    }

    @Override
    public int updatePassword(int uid) {
        return usersMapper.updatePassword(uid);
    }

}
