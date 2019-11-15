package com.test.controller.usersController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.pojo.*;
import com.test.service.userService.EmployeeService;
import com.test.service.userService.UsersService;
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
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UsersService usersService;


    @RequestMapping("info")
    public String getinfo(){
        return "employeetable";
    }


    @RequestMapping("getAllEmployee")
    public String getAllEmployee(@RequestParam(defaultValue = "1") int pageNum, Model model, HttpSession session){

//        int intpageNum=0,intpageNum1=0,intpageNum2=0;
//
//        if(pageNum!=null){
//            session.setAttribute("pageNum",pageNum);
//        }
//        if(pageNum1!=null){
//            session.setAttribute("pageNum1",pageNum1);
//        }
//        if(pageNum2!=null){
//            session.setAttribute("pageNum2",pageNum2);
//        }
//
//        if(pageNum==null){
//            if(session.getAttribute("pageNum")==null){
//                intpageNum=1;
//                session.setAttribute("pageNum","1");
//            }else {
//                intpageNum= Integer.parseInt((String)session.getAttribute("pageNum"));
//                pageNum = String.valueOf(intpageNum);
//                session.setAttribute("pageNum",pageNum);
//            }
//
//        }
//        if (pageNum1==null){
//            if(session.getAttribute("pageNum1")==null){
//                intpageNum1=1;
//                session.setAttribute("pageNum1","1");
//            }else {
//                intpageNum1= Integer.parseInt((String)session.getAttribute("pageNum1"));
//                pageNum1 = String.valueOf(intpageNum1);
//                session.setAttribute("pageNum1",pageNum1);
//            }
//        }
//        if(pageNum2==null){
//            if(session.getAttribute("pageNum2")==null){
//                intpageNum2=1;
//                session.setAttribute("pageNum2","1");
//            }else {
//                intpageNum2= Integer.parseInt((String)session.getAttribute("pageNum2"));
//                pageNum2 = String.valueOf(intpageNum2);
//                session.setAttribute("pageNum2",pageNum2);
//            }
//        }



        int[]page = new int[3];
        page[0]=pageNum;
        page[1]=pageNum;
        page[2]=pageNum;
        Map<String, Object> employee = employeeService.getEmployee(page);


        List<Teacher> teacherList = (List<Teacher>) employee.get("teacherList");
        PageInfo<Teacher> pageInfo = new PageInfo<Teacher>(teacherList);


        List<ClassLeader> classLeaderList = (List<ClassLeader>) employee.get("classLeaderList");
        PageInfo<ClassLeader> pageInfo1 = new PageInfo<ClassLeader>(classLeaderList);

        List<SchoolManager> schoolManagerList = (List<SchoolManager>) employee.get("schoolManagerList");
        PageInfo<SchoolManager> pageInfo2 = new PageInfo<SchoolManager>(schoolManagerList);

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("pageInfo",pageInfo);
        map.put("pageInfo1",pageInfo1);
        map.put("pageInfo2",pageInfo2);
//        model.addAttribute("pageInfo",pageInfo);
        model.addAllAttributes(map);


        return "employeetable";
    }

    @RequestMapping("changeTeacher")
    public String changeTeacher(Teacher teacher){
        int i = employeeService.changeEmployee(teacher);
        return "redirect:/getAllEmployee";
    }

    @RequestMapping("changeClassLeader")
    public String changeClassLeader(ClassLeader classLeader){
        int i  = employeeService.changeEmployee(classLeader);
        return "redirect:/getAllEmployee";
    }

    @RequestMapping("changeSchoolManager")
    public String changeSchoolManager(SchoolManager schoolManager){
        int i = employeeService.changeEmployee(schoolManager);
        return "redirect:/getAllEmployee";
    }

    @RequestMapping("deleteTeacher")
    public String deleteTeacher(int tid){
        Teacher teacher = new Teacher();
        teacher.setTid(tid);
        employeeService.deleteTeahcerUser(tid);
        employeeService.deleteEmployee(teacher);

        return "redirect:/getAllEmployee";
    }

    @RequestMapping("deleteClassLeader")
    public String deleteClassLeader(int clid){
        ClassLeader classLeader = new ClassLeader();
        classLeader.setClid(clid);
        employeeService.deleteClassLeaderUser(clid);
        employeeService.deleteEmployee(classLeader);

        return "redirect:/getAllEmployee";
    }

    @RequestMapping("deleteSchoolManager")
    public String deleteSchoolManager(int smid){
        SchoolManager schoolManager = new SchoolManager();
        schoolManager.setSmid(smid);
        employeeService.deleteSchoolManagerUser(smid);
        employeeService.deleteEmployee(schoolManager);

        return "redirect:/getAllEmployee";
    }

    @RequestMapping("addEmployee")
    public String addEmployee(String name,String role){
        if(role.equals("teacher")){

            /**
             * 添加user账户
             */
            User user = new User();
            user.setUname(name);
            user.setUpwd("123456");
            Role role1 = new Role();
            role1.setRoleid(2);
            role1.setRolename("teacher");
            user.setRole(role1);
            int uid = usersService.addUser(user);
            User user1 = new User();
            user1.setUid(uid);
            /**
             * 添加教师信息,包含uid
             */
            Teacher teacher = new Teacher();
            teacher.setTname(name);
            teacher.setUser(user1);

            int i = employeeService.addEmployee(teacher);
            if(i>0){
                return "redirect:/getAllEmployee";
            }else
                return "redirect:/getAllEmployee";

        }else if (role.equals("classboss")){

            User user = new User();
            user.setUname(name);
            user.setUpwd("123456");
            Role role1 = new Role();
            role1.setRoleid(5);
            role1.setRolename("classboss");
            user.setRole(role1);
            int uid = usersService.addUser(user);
            User user1 = new User();
            user1.setUid(uid);

            ClassLeader classLeader = new ClassLeader();
            classLeader.setClname(name);
            classLeader.setUser(user1);
            employeeService.addEmployee(classLeader);
            return "redirect:/getAllEmployee";
        }else if (role.equals("schoolboss")){

            User user = new User();
            user.setUname(name);
            user.setUpwd("123456");
            Role role1 = new Role();
            role1.setRoleid(4);
            role1.setRolename("schoolboss");
            user.setRole(role1);
            int uid = usersService.addUser(user);
            User user1 = new User();
            user1.setUid(uid);

            SchoolManager schoolManager = new SchoolManager();
            schoolManager.setSmname(name);
            schoolManager.setUser(user1);
            employeeService.addEmployee(schoolManager);
            return "redirect:/getAllEmployee";
        }else{
            System.out.println("类型匹配异常");
            return "redirect:/getAllEmployee";
        }


    }

    @RequestMapping("getAllEmployeeByLike")
    public String getAllEmployeeByLike(String name,@RequestParam(defaultValue = "1") int pageNum3, @RequestParam(defaultValue = "1")int pageNum4, @RequestParam(defaultValue = "1")int pageNum5, Model model, HttpSession session){

        if(name!=null){
            session.setAttribute("name",name);
        }

        name= (String) session.getAttribute("name");


        if(pageNum3!=1){
            session.setAttribute("pageNum",pageNum3);
        }
        if(pageNum4!=1){
            session.setAttribute("pageNum1",pageNum4);
        }
        if(pageNum5!=1){
            session.setAttribute("pageNum2",pageNum5);
        }

        if(pageNum3==1){
            if(session.getAttribute("pageNum3")==null){
                pageNum3=pageNum3;
            }else {
                pageNum3= (Integer) session.getAttribute("pageNum3");
            }

        }
        if (pageNum4==1){
            if(session.getAttribute("pageNum4")==null){
                pageNum4=pageNum4;
            }else {
                pageNum4= (Integer) session.getAttribute("pageNum4");
            }
        }
        if(pageNum5==1){
            if(session.getAttribute("pageNum5")==null){
                pageNum5=pageNum5;
            }else {
                pageNum5= (Integer) session.getAttribute("pageNum5");
            }
        }
        int[]page = new int[3];
        page[0]=pageNum3;
        page[1]=pageNum4;
        page[2]=pageNum5;

        Map<String, Object> employee = employeeService.getEmployeeByLike(name, page);


        List<Teacher> teacherList = (List<Teacher>) employee.get("teacherList");
        PageInfo<Teacher> pageInfo = new PageInfo<Teacher>(teacherList);


        List<ClassLeader> classLeaderList = (List<ClassLeader>) employee.get("classLeaderList");
        PageInfo<ClassLeader> pageInfo1 = new PageInfo<ClassLeader>(classLeaderList);


        List<SchoolManager> schoolManagerList = (List<SchoolManager>) employee.get("schoolManagerList");
        PageInfo<SchoolManager> pageInfo2 = new PageInfo<SchoolManager>(schoolManagerList);

        System.out.println();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("pageInfo",pageInfo);
        map.put("pageInfo1",pageInfo1);
        map.put("pageInfo2",pageInfo2);
//        model.addAttribute("pageInfo",pageInfo);
        map.put("like","like");
        model.addAllAttributes(map);


        return "employeetable";

    }
}
