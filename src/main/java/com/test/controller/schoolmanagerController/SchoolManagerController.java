package com.test.controller.schoolmanagerController;

import com.test.pojo.SchoolManager;
import com.test.pojo.User;
import com.test.service.schoolmanagerService.SchoolManagerService;
import com.test.service.userService.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class SchoolManagerController {
    @Autowired
    private SchoolManagerService schoolManagerService;
    @Autowired
    private UsersService usersService;

    /**
     * 跳转到修改密码页面
     * @param session
     * @return
     */
    @RequestMapping("editUpwd")
    public String updateUpwd(HttpSession session,Model model){
        User user = (User) session.getAttribute("user");
        SchoolManager schoolManager = schoolManagerService.getSchoolManagerByUid(user.getUid());
        model.addAttribute("schoolManager",schoolManager);
        return "redirect:updateUpwd";
    }

    /**
     * 修改密码
     * @param upwd
     * @param newPwd
     * @param cfgPwd
     * @return
     */
    @RequestMapping("updateUpwd")
    public String editUpwd(String upwd, String newPwd, String cfgPwd){
        int i = usersService.updateUserPwd(upwd,newPwd,cfgPwd);
        if(i>0){
            return "redirect:index";
        }
        return "redirect:editUpwd";

    }

}
