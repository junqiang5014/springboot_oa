package com.test.controller.usersController;

import com.test.pojo.User;
import com.test.service.userService.UsersService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SubjectDAO;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

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
                User user1 = usersService.getUserByUname(user.getUname());
                session.setAttribute("user",user1);
                return "redirect:/getindex";
            }
        }catch (Exception e){
            return "redirect:/getlogin";
        }

        return "redirect:/getlogin";
    }

    @RequestMapping("getindex")
    public String getindex(){
        return "index";
    }

}
