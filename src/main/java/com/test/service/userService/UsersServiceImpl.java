package com.test.service.userService;

import com.test.mapper.userMapper.UsersMapper;
import com.test.pojo.User;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
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
//        if(!user.getUpwd().equals(md5Hash.toHex())){
//            throw new IllegalArgumentException("输入的旧密码不正确！");
//        }
        //判断新旧密码是否一致
        if(upwd.equals(newPwd)){
            throw new IllegalArgumentException("新密码不能与旧密码相同！");
        }
        //更新密码
        md5Hash=new Md5Hash(newPwd);
        int i = usersMapper.updateUpwdByUser(md5Hash.toString(), user.getUname());

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
    public int updatePassword(User user) {
        return usersMapper.updatePassword(user);
    }

    @Override
    public int addUser(User user) {
        String uname = getPingYin(user.getUname());
        String upwd = new Md5Hash(user.getUpwd()).toString();
        user.setUname(uname);
        user.setUpwd(upwd);
        int i = usersMapper.addUser(user);
        if(i>0){
            return user.getUid();
        }else {
            return -1;
        }

    }

    @Override
    public int getRoleById(int uid) {
        return usersMapper.getRoleById(uid);
    }

    @Override
    public String getRoleNameByRoleId(int roleid) {
        return usersMapper.getRoleNameByRoleId(roleid);
    }


    public static String getPingYin(String src) {
        char[] t1 = null;
        t1 = src.toCharArray();
        String[] t2 = new String[t1.length];
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);
        String t4 = "";
        int t0 = t1.length;
        try {
            for (int i = 0; i < t0; i++) {
                // 判断是否为汉字字符
                if (java.lang.Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
                    t4 += t2[0];
                } else {
                    t4 += java.lang.Character.toString(t1[i]);
                }
            }
            return t4;
        } catch (BadHanyuPinyinOutputFormatCombination e1) {
            e1.printStackTrace();
        }
        return t4.toLowerCase();
    }


}
