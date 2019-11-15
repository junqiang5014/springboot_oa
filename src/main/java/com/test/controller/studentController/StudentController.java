package com.test.controller.studentController;

import com.test.pojo.Holiday;
import com.test.pojo.Report;
import com.test.pojo.Student;
import com.test.pojo.User;
import com.test.service.holidayService.HolidayService;
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
    @Autowired
    private HolidayService holidayService;

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

    /**
     * 学生列表
     * @param model
     * @return
     */
    @RequestMapping("student/getStudent")
    public String getStudent(Model model){
        List<Student> studentAndClassList = studentService.getStudentAndClassList();
        model.addAttribute("studentAndClassList",studentAndClassList);
        return "student/stuList";
    }

    /**
     *跳转到修改页面
     * @param model
     * @param stuid
     * @return
     */
    @RequestMapping("student/editstudent")
    public String editstudent(Model model,int stuid){
        Student student = studentService.getStudentByStuid(stuid);
        model.addAttribute(student);
        return "student/updatemessage";
    }

    /**
     * 修改学生信息
     * @param student
     * @param session
     * @return
     */
    @RequestMapping("student/updatestudent")
    public String updatestudent(Student student, HttpSession session){
        User user=(User) session.getAttribute("user1");
        int i = studentService.updateStudentByUser(student,user);
        if(i>0){
            return "redirect:student/studentindex";
        }
        return "redirect:student/getStudentmessage";

    }

    @RequestMapping("student/studentindex")
    public String studentindex(){
        return "student/studentindex";
    }

    /**
     * 查询学生信息
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("student/getStudentmessage")
    public String getStudentByUser(HttpSession session,Model model){
        System.out.println("studnt");
        User user = (User)session.getAttribute("user");
        Student student = studentService.getStudentByUser(user);
        model.addAttribute("student",student);
        return "student/updatemessage";

    }

    /**
     * 跳转到增加页面
     * @return
     */
    @RequestMapping("student/saveReport")
    public String saveReport(){
        System.out.println("student222");
        return "student/add";

    }

    /**
     * 跳转到周报管理页面
     * @return
     */
    @RequestMapping("student/getreportmessage")
    public String getreportmessage(){
        return "student/reportmessage";
    }

    /**
     * 增加周报
     * @param report
     * @return
     */
    @RequestMapping("student/addReport")
        public String addReport(Report report){
        int i = reportService.addReport(report);
        if(i>0){
            return "redirect:student/getreportmessage";
        }
        return "redirect:student/saveReport";
    }

    /**
     * 删除周报
     * @param rid
     * @return
     */
    @RequestMapping("student/deleteReport")
    @ResponseBody
    public String deleteReport(int rid,Student student){
        int i = reportService.deleteReportByRid(rid,student);
        if(i>0){
            return "success";

        }
        return "fail";

    }

    /**
     * 周报列表
     * @param model
     * @return
     */
    @RequestMapping("student/getReport")
    public String getReport(Model model){
        List<Report> reportList = reportService.getReportList();
        System.out.println(reportList);
        model.addAttribute("reportList",reportList);
        return "student/resportlist";
    }


    /**
     * 跳转到修改密码页面
     * @param session
     * @return
     */
    @RequestMapping("student/editUpwd")
    public String updateUpwd(HttpSession session,Model model){
        User user = (User) session.getAttribute("user");
        String upwd = user.getUpwd();
        model.addAttribute("user",user);
        return "student/updateUpwd";
    }

    /**
     * 修改密码
     * @param upwd
     * @param newPwd
     * @param cfgPwd
     * @return
     */
    @RequestMapping("student/updateUpwd")
    public String editUpwd(String upwd, String newPwd, String cfgPwd){
        int i = usersService.updateUserPwd(upwd,newPwd,cfgPwd);
        if(i>0){
            return "student/studentindex";
        }
        return "redirect:student/editUpwd";

    }

    /**
     * 学生请假
     * @return
     */
    @RequestMapping("student/addHoliday")
    public String addHoliday(Holiday holiday,HttpSession session,Model model){
        User user=(User)session.getAttribute("user");
        Student student = studentService.getStudentByUid(user.getUid());
        model.addAttribute("student",student);
        int i = holidayService.addHoliday(holiday);
        if(i>0){
            return "student/studentindex";
        }
        return "student/holiday";
    }







}
