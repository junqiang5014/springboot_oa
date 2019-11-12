package com.test.controller.studentController;

import com.test.pojo.Report;
import com.test.pojo.Student;
import com.test.pojo.User;
import com.test.service.reportService.ReportService;
import com.test.service.studentService.StudentService;
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
        return "update";
    }

    @RequestMapping("updatestudent")
    public String updatestudent(Student student, HttpSession session){
        User user=(User) session.getAttribute("user1");
        int i = studentService.updateStudentByUser(student,user);
        if(i>0){
            return "redirect:index";
        }
        return "redirect:editstudent?stuid="+student.getStuid();

    }

    @RequestMapping("getStudentByUser")
    public String getStudentByUser(HttpSession session,Model model){
        User user = (User)session.getAttribute("user1");
        Student student = studentService.getStudentByUser(user);
        model.addAttribute(student);
        return "update";

    }



    @RequestMapping("saveReport")
    public String saveReport(){
        return "add";

    }

    @RequestMapping("addReport")
        public String addReport(Report report){
        int i = reportService.addReport(report);
        if(i>0){
            return "redirect:";
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

    @RequestMapping("getReportList")
    public String getReportList(Model model){
        List<Report> reportList = reportService.getReportList();
        model.addAttribute(reportList);
        return "resportlist";
    }





}
