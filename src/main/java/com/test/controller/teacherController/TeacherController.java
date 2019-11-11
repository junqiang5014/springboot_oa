package com.test.controller.teacherController;

import com.test.service.teacherService.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller("teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping("changeUpwdPage")
    public String changeUpwdPage(HttpSession session){
//        User user = (User) session.getAttribute("user");
        return "changeupwd";
    }
}