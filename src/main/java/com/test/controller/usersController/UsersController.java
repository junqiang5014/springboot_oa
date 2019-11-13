package com.test.controller.usersController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.pojo.User;
import com.test.service.userService.UsersService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private SecurityManager securityManager;

    @RequestMapping("loginPage")
    public String getlogin(){
        return "login";
    }

    @RequestMapping("login")
    public String login(User user, HttpSession session){
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUname(),user.getUpwd());
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(usernamePasswordToken);
            if(subject.isAuthenticated()){
                System.out.println("登录成功");
                User user1 = usersService.getUserByUname(user.getUname());
                session.setAttribute("user",user1);
                return "redirect:/getindex";
            }
        }catch (Exception e){
            System.out.println("登录失败");
            return "redirect:getlogin";
        }

        return "redirect:getlogin";
    }

    @RequestMapping("getindex")
    public String getindex(){
        return "/index";
    }

    @RequestMapping("exit")
    public String exit(HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user!=null){
            session.removeAttribute("user");
        }

        return "loginPage";
    }



    @RequestMapping("getAllUser")
    public String getAllUser(@RequestParam(defaultValue = "1") int pageNum, Model model){

        PageHelper.startPage(pageNum,5);
        List<User> userList = usersService.getUserAndRole();

        PageInfo<User> pageInfo = new PageInfo<User>(userList);

        model.addAttribute("pageInfo",pageInfo);

        return "usertable";


    }

    @RequestMapping("changeUpwd1")
    public String changeUpwd(User user){
        String upwd = user.getUpwd();
        String newupwd  = new Md5Hash(upwd).toString();
        user.setUpwd(newupwd);
        int i = usersService.updatePassword(user);
        if(i>0){
            System.out.println("修改成功");
            return "redirect:/getAllUser";
        }
        return "redirect:/getAllUser";

    }

    @RequestMapping("deleteUser1")
    public String deleteUser(String pageNum,String uid){

        int i = usersService.deleteUser(Integer.parseInt(uid));
        System.out.println(i);
        return "redirect:/getAllUser";

    }

    @RequestMapping("getUserByLikeUname")
    public String getUserByLikeUname(@RequestParam(defaultValue = "1") int pageNum, String uname,Model model){
        PageHelper.startPage(pageNum,5);
        List<User> userList = usersService.getUserByLikeUname(uname);
        PageInfo<User> pageInfo = new PageInfo<User>(userList);
//        model.addAttribute("like","like");
//        model.addAttribute("pageInfo",pageInfo);

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("like","like");
        map.put("pageInfo",pageInfo);
        map.put("uname",uname);
        model.addAllAttributes(map);
        return "usertable";
    }

}
