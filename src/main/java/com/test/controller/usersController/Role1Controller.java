package com.test.controller.usersController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.pojo.Course;
import com.test.pojo.Role;
import com.test.service.userService.Role1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class Role1Controller {

    @Autowired
    private Role1Service role1Service;


    @RequestMapping("getallroles")
    public String getallroles(@RequestParam(defaultValue = "1") int pageNum, Model model){
        PageHelper.startPage(pageNum,5);
        List<Role> roleList = role1Service.getRole();
        PageInfo<Role> pageInfo = new PageInfo<Role>(roleList);
        model.addAttribute("pageInfo",pageInfo);
        return "roletable";
    }


    @RequestMapping("deleteRole")
    public String deleteCourse(String roleid,String pageNum){

        int i = role1Service.deleteRole(Integer.parseInt(roleid));
        if(i>0){
            System.out.println("删除成功");
            return "redirect:/getallroles?pageNum="+pageNum;
        }else
            return "redirect:/getallroles?pageNum="+pageNum;
    }

    @RequestMapping("changeRole")
    public String changeCourse(Role role){
        System.out.println(role);
        role1Service.updateRole(role);
        return "redirect:/getallroles";
    }


    @RequestMapping("addRole")
    public String addCourse(Role role){
        role1Service.addRole(role.getRolename());
        return "redirect:/getallroles";
    }

}
