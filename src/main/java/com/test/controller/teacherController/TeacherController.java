package com.test.controller.teacherController;

import com.test.pojo.*;
import com.test.service.courseService.CourseService;
import com.test.service.holidayService.HolidayService;
import com.test.service.reportService.ReportService;
import com.test.service.scoreService.ScoreService;
import com.test.service.studentService.StudentService;
import com.test.service.teacherService.TeacherService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller("teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private ReportService reportService;
    @Autowired
    private HolidayService holidayService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private ScoreService scoreService;

    @RequestMapping("changeUpwdPage")
    public String changeUpwdPage(HttpSession session){
//        User user = (User) session.getAttribute("user");
        return "changeupwd";
    }

    @RequestMapping("reportListPage")
    public String reportListPage(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        Teacher teacher = teacherService.getTeacherByUid(user.getUid());
        List<Task> taskList = taskService.createTaskQuery().taskAssignee(teacher.getTname()).list();
        if (taskList.size() != 0) {
            List<Report> list = new ArrayList<Report>();
            for (Task t : taskList) {
                ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                        .processInstanceId(t.getProcessInstanceId()).singleResult();
                int rid = Integer.parseInt(processInstance.getBusinessKey());
                Report report = reportService.getReportByRid(rid);
                list.add(report);
            }
            model.addAttribute("reportList", list);
        }
        return "reportList";
    }

    @RequestMapping("reportPage")
    public String repotrPage(int rid,Model model){
        Report report = reportService.getReportByRid(rid);
        model.addAttribute("report", report);
        return "report";
    }

    @RequestMapping("updateReport")
    public String updateReport(Report report){
        int i = reportService.updateReport(report);
        return "success";
    }

    @RequestMapping("holidayListPage")
    public String holidayListPage(HttpSession session,Model model){
        User user = (User) session.getAttribute("user");
        Teacher teacher = teacherService.getTeacherByUid(user.getUid());
        List<Task> taskList = taskService.createTaskQuery().taskAssignee(teacher.getTname()).list();
        if (taskList.size() != 0) {
            ArrayList<Holiday> holidays = new ArrayList<>();
            for (Task task : taskList) {
                ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                        .processInstanceId(task.getProcessInstanceId()).singleResult();
                int hid = Integer.parseInt(processInstance.getBusinessKey());
                Holiday holiday = holidayService.getHolidayByHid(hid);
                holidays.add(holiday);
            }
            model.addAttribute("holidayList", holidays);
        }
        return "holidayList";
    }

    @RequestMapping("holidayPage")
    public String holidayPage(int hid,Model model){
        Holiday holiday = holidayService.getHolidayByHid(hid);
        model.addAttribute("holiday", holiday);
        return "holiday";
    }

    @RequestMapping("approveHoliday")
    public String approveHoliday(int hid){
        holidayService.changeStateByHid(hid);
        return "redirect:holidayListPage";
    }

    @RequestMapping("scoreStudentListPage")
    public String scoreEnteringPage(HttpSession session,Model model){
        User user = (User) session.getAttribute("user");
        Teacher teacher = teacherService.getTeacherByUid(user.getUid());
        //找到该老师所在班级的学生列表
        List<Student> studentList = teacherService.getStudentListByTid(teacher.getTid());
        //找到老师的课程
//        Course course = courseService.getCourseByTid(teacher.getTid());
        model.addAttribute("studentList", studentList);
//        model.addAttribute("course", course);
        return "scoreStudentList";
    }

    @RequestMapping("studentScoreListPage")
    public String scoreEntering(int stuid,Model model){
        //根据stuid找到该学生的成绩
        List<Score> scoreList = scoreService.getScoreListByStuid(stuid);
        if (scoreList.size()!=0){
            model.addAttribute("scoreList", scoreList);
        }
        return "studentScoreList";
    }

    @RequestMapping("addScorePage")
    public String addScore(int stuid,int courseid,Model model){
        Score score = new Score();
        score.setStudent(studentService.getStudentByStuid(stuid));
        score.setCourse(courseService.getCourseByCourseid(courseid));
        model.addAttribute("score", score);
        return "addScore";
    }

    @RequestMapping("addScore")
    public String addScore(Score score,int stuid,int couresid){
        Student student = studentService.getStudentByStuid(stuid);
        Course course = courseService.getCourseByCourseid(couresid);
        score.setStudent(student);
        score.setCourse(course);
        int i = scoreService.addScore(score);
        return "scoreStudentListPage";
    }



}