package com.test.controller.usersController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsersController {

    @RequestMapping("loginPage")
    public String getlogin(){
        return "login";
    }
}
