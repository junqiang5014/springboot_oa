package com.test.controller.userController1;

import com.test.pojo.User;
import com.test.service.userService1.UserService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController1 {

    @Autowired
    private UserService1 userService1;

    @RequestMapping("changeUpwd")
    public String changeUpwd(User user){
        userService1.updateUser(user);
        return "index";
    }

}
