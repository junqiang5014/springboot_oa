package com.test.controller.usersController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.pojo.ClassLeader;
import com.test.pojo.Classes;
import com.test.pojo.Teacher;
import com.test.service.userService.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ClassController {

    @Autowired
    private ClassService classService;

    @RequestMapping("getClassTable")
    public String getClassTable() {
        return "classtable";
    }

    @RequestMapping("getClassInfo")
    public String getClassinfo(int cid){
        return "redirect:/changeClass?cid="+cid;
    }

    @RequestMapping("getAllClass")
    public String getAllClass(@RequestParam(defaultValue = "1") int pageNum, Model model){
        PageHelper.startPage(pageNum,5);
        List<Classes> classesList = classService.getAllClass();
        PageInfo<Classes> pageInfo = new PageInfo<Classes>(classesList);
        model.addAttribute("pageInfo",pageInfo);
        return "classtable";

    }

    @RequestMapping("deleteClass")
    public String deleteClass(int cid){
        classService.deleteClass(cid);
        return "redirect:/getAllClass";
    }

    @RequestMapping("addClass")
    public String addClass(String cname,String tname,String clname){
        Classes classes = new Classes();
        classes.setCname(cname);
        if(tname!=null && !tname.equals("")){
            int tid;
            try {
                tid = classService.getTid(tname);
            }catch (Exception e){
                return "redirect:/getaddclass";
            }
            Teacher teacher = new Teacher();
            teacher.setTname(tname);
            teacher.setTid(tid);
            classes.setTeacher(teacher);
        }else {
            Teacher teacher = new Teacher();
            classes.setTeacher(teacher);
        }

        if(clname!=null&&!clname.equals("")){
            int clid;
            try {
                clid = classService.getClid(clname);
            }catch (Exception e){
                return "redirect:/getaddclass";
            }

            ClassLeader classLeader = new ClassLeader();
            classLeader.setClid(clid);
            classLeader.setClname(clname);
            classes.setClassLeader(classLeader);
        }else {
            ClassLeader classLeader = new ClassLeader();
            classes.setClassLeader(classLeader);
        }
        classService.addClass(classes);
        return "redirect:/getAllClass";
    }

    @RequestMapping("getaddclass")
    public String getaddclass(){
        return "addclass";
    }


    @RequestMapping("changeClass")
    public String changeClass(int cid,Model model){
        Classes classes = classService.getClassByid(cid);
        model.addAttribute("class",classes);
        return "classinfo";
    }

    @RequestMapping("changeClassInfo")
    public String changeClassInfo(int cid, String cname,String tname,String clname){
        Classes classes = new Classes();
        classes.setCid(cid);
        classes.setCname(cname);
        if(tname!=null && !tname.equals("")){
            int tid;
            try {
                tid = classService.getTid(tname);
            }catch (Exception e){
                return "redirect:/getAllClass";
            }
            Teacher teacher = new Teacher();
            teacher.setTname(tname);
            teacher.setTid(tid);
            classes.setTeacher(teacher);
        }else {
            Teacher teacher = new Teacher();
            classes.setTeacher(teacher);
        }

        if(clname!=null&&!clname.equals("")){
            int clid;
            try {
                clid = classService.getClid(clname);
            }catch (Exception e){
                return "redirect:/getAllClass";
            }

            ClassLeader classLeader = new ClassLeader();
            classLeader.setClid(clid);
            classLeader.setClname(clname);
            classes.setClassLeader(classLeader);
        }else {
            ClassLeader classLeader = new ClassLeader();
            classes.setClassLeader(classLeader);
        }
        classService.updateClass(classes);
        return "redirect:/getAllClass";
    }
}
