package com.test.controller.usersController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.pojo.Course;
import com.test.service.userService.Course1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CourseController {

    @Autowired
    private Course1Service courseService;

    /**
     * 跳转课程管理页面
     * @return
     */
    @RequestMapping("course")
    public String getcoursepage(){
        return "coursetable";
    }

    @RequestMapping("getallcourses")
    public String getAllCourses(@RequestParam(defaultValue = "1") int pageNum, Model model){
        PageHelper.startPage(pageNum,5);
        List<Course> courseList = courseService.getCourse();
        System.out.println(courseList);
        PageInfo<Course> pageInfo = new PageInfo<Course>(courseList);
        model.addAttribute("pageInfo",pageInfo);
        return "coursetable";
    }


    @RequestMapping("deleteCourse")
    public String deleteCourse(int courseid,int pageNum){

        System.out.println(courseid);
        System.out.println(pageNum);
        int i = courseService.deleteCourse(courseid);
        if(i>0){
            System.out.println("删除成功");
            return "redirect:/getallcourses?pageNum="+pageNum;
        }else
            return "redirect:/getallcourses?pageNum="+pageNum;
    }

}
