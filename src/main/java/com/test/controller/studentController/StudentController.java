package com.test.controller.studentController;

import com.test.pojo.Report;
import com.test.pojo.Student;
import com.test.pojo.User;
import com.test.service.reportService.ReportService;
import com.test.service.studentService.StudentService;
import com.test.service.userService.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private ReportService reportService;
    @Autowired
    private UsersService usersService;

    public ReportService getReportService() {
        return reportService;
    }

    public void setReportService(ReportService reportService) {
        this.reportService = reportService;
    }

    public StudentService getStudentService() {
        return studentService;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping("getStudent")
    public String getStudent(Model model){
        List<Student> studentAndClassList = studentService.getStudentAndClassList();
        model.addAttribute("studentAndClassList",studentAndClassList);
        return "stuList";
    }

    @RequestMapping("editstudent")
    public String editstudent(Model model,int stuid){
        Student student = studentService.getStudentByStuid(stuid);
        model.addAttribute(student);
        return "updatemessage";
    }

    @RequestMapping("updatestudent")
    public String updatestudent(Student student, HttpSession session){
        User user=(User) session.getAttribute("user1");
        int i = studentService.updateStudentByUser(student,user);
        if(i>0){
            return "redirect:studentindex";
        }
        return "redirect:getStudentmessage";

    }

    @RequestMapping("getStudentmessage")
    public String getStudentByUser(HttpSession session,Model model){
        User user = (User)session.getAttribute("user1");
        Student student = studentService.getStudentByUser(user);
        model.addAttribute("student",student);
        return "updatemessage";

    }





    @RequestMapping("saveReport")
    public String saveReport(){
        return "add";

    }

    @RequestMapping("getreportmessage")
    public String getreportmessage(){
        return "reportmessage";
    }

    @RequestMapping("addReport")
        public String addReport(Report report){
        int i = reportService.addReport(report);
        if(i>0){
            return "redirect:getreportmessage";
        }
        return "redirect:saveReport";
    }

    @RequestMapping("deleteReport")
    @ResponseBody
    public String deleteReport(int rid){
        int i = reportService.deleteReportByRid(rid);
        if(i>0){
            return "success";

        }
        return "fail";

    }

    @RequestMapping("getReport")
    public String getReport(Model model){
        List<Report> reportList = reportService.getReportList();
        model.addAttribute("reportList",reportList);
        return "resportlist";
    }



    @RequestMapping("editUpwd")
    public String updateUpwd(HttpSession session){
        User user = (User) session.getAttribute("user1");
        String upwd = usersService.getUpwdByUname(user.getUname());
        return "redirect:updateUpwd";
    }

    @RequestMapping("updateUpwd")
    public String editUpwd(User user){
        int i = usersService.updateUpwdByUser(user);
        if(i>0){
            return "redirect:index";
        }
        return "redirect:editUpwd";

    }





}
