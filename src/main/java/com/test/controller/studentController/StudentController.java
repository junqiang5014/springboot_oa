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
    @RequestMapping("getStudent")
    public String getStudent(Model model){
        List<Student> studentAndClassList = studentService.getStudentAndClassList();
        model.addAttribute("studentAndClassList",studentAndClassList);
        return "stuList";
    }

    /**
     *跳转到修改页面
     * @param model
     * @param stuid
     * @return
     */
    @RequestMapping("editstudent")
    public String editstudent(Model model,int stuid){
        Student student = studentService.getStudentByStuid(stuid);
        model.addAttribute(student);
        return "updatemessage";
    }

    /**
     * 修改学生信息
     * @param student
     * @param session
     * @return
     */
    @RequestMapping("updatestudent")
    public String updatestudent(Student student, HttpSession session){
        User user=(User) session.getAttribute("user1");
        int i = studentService.updateStudentByUser(student,user);
        if(i>0){
            return "redirect:studentindex";
        }
        return "redirect:getStudentmessage";

    }

    /**
     * 查询学生信息
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("getStudentmessage")
    public String getStudentByUser(HttpSession session,Model model){
        User user = (User)session.getAttribute("user1");
        Student student = studentService.getStudentByUser(user);
        model.addAttribute("student",student);
        return "updatemessage";

    }

    /**
     * 跳转到增加页面
     * @return
     */
    @RequestMapping("saveReport")
    public String saveReport(){
        return "add";

    }

    /**
     * 跳转到周报管理页面
     * @return
     */
    @RequestMapping("getreportmessage")
    public String getreportmessage(){
        return "reportmessage";
    }

    /**
     * 增加周报
     * @param report
     * @return
     */
    @RequestMapping("addReport")
        public String addReport(Report report){
        int i = reportService.addReport(report);
        if(i>0){
            return "redirect:getreportmessage";
        }
        return "redirect:saveReport";
    }

    /**
     * 删除周报
     * @param rid
     * @return
     */
    @RequestMapping("deleteReport")
    @ResponseBody
    public String deleteReport(int rid){
        int i = reportService.deleteReportByRid(rid);
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
    @RequestMapping("getReport")
    public String getReport(Model model){
        List<Report> reportList = reportService.getReportList();
        model.addAttribute("reportList",reportList);
        return "resportlist";
    }


    /**
     * 跳转到修改密码页面
     * @param session
     * @return
     */
    @RequestMapping("editUpwd")
    public String updateUpwd(HttpSession session){
        User user = (User) session.getAttribute("user1");
        String upwd = usersService.getUpwdByUname(user.getUname());
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

    /**
     * 学生请假
     * @return
     */
    @RequestMapping("addHoliday")
    public String addHoliday(Holiday holiday,HttpSession session,Model model){
        User user=(User)session.getAttribute("user");
        Student student = studentService.getStudentByUid(user.getUid());
        model.addAttribute("student",student);
        int i = holidayService.addHoliday(holiday);
        if(i>0){
            return "";
        }
        return "";
    }







}
