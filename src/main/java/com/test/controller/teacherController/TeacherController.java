package com.test.controller.teacherController;

import com.test.pojo.*;
import com.test.service.classesService.ClassesService;
import com.test.service.courseService.CourseService;
import com.test.service.holidayService.HolidayService;
import com.test.service.reportService.ReportService;
import com.test.service.scoreService.ScoreService;
import com.test.service.studentService.StudentService;
import com.test.service.student_holidayService.Student_holidayService;
import com.test.service.teacherService.TeacherService;
import com.test.service.teacher_holidayService.Teacher_holidayService;
import com.test.service.userService1.UserService1;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("teacher")
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
    @Autowired
    private ClassesService classesService;
    @Autowired
    private Teacher_holidayService teacher_holidayService;
    @Autowired
    private UserService1 userService1;
    @Autowired
    private Student_holidayService student_holidayService;

    /**
     * 老师主页
     */
    @RequestMapping("index")
    public String index(HttpSession session,Model model){
        User user = (User) session.getAttribute("user");
        Teacher teacher = teacherService.getTeacherByUid(user.getUid());
        model.addAttribute("teacher", teacher);
        return "teacher/teacherindex";
    }

//    @RequestMapping("teacherindex")
//    public String teacherindex(HttpSession session,Model model){
//        User user = (User) session.getAttribute("user");
//        Teacher teacher = teacherService.getTeacherByUid(user.getUid());
//        model.addAttribute("teacher", teacher);
//        return "teacher/teacherindex";
//    }

    /**
     * 更改密码
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("changeUpwdPage")
    public String changeUpwdPage(HttpSession session,Model model){
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "teacher/teacherChangeUpwd";
    }

    @RequestMapping("changeUpwd")
    public String changeUpwd(User user){
        Md5Hash md5Hash = new Md5Hash(user.getUpwd());
        user.setUpwd(md5Hash.toString());
        userService1.updateUser(user);
        return "teacher/success";
    }

    /**
     * 带审批周报列表
     * @param session
     * @param model
     * @return
     */
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
        return "teacher/reportList";
    }

    /**
     * 单个周报页面
     * @param rid
     * @param model
     * @return
     */
    @RequestMapping("reportPage")
    public String repotrPage(int rid,Model model){
        Report report = reportService.getReportByRid(rid);
        model.addAttribute("report", report);
        return "teacher/reportinfo";
    }

    /**
     * 审批周报
     * @param report
     * @return
     */
    @RequestMapping("updateReport")
    public String updateReport(Report report){
        int i = reportService.updateReport(report);
        return "teacher/success";
    }

    /**
     * 假条列表页面
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("holidayListPage")
    public String holidayListPage(HttpSession session,Model model){
        User user = (User) session.getAttribute("user");
        Teacher teacher = teacherService.getTeacherByUid(user.getUid());
        List<Task> taskList = taskService.createTaskQuery().taskAssignee(teacher.getTname()).list();
        if (taskList.size() != 0) {
            ArrayList<Holiday> holidays = new ArrayList<>();
            List<Student_holiday> student_holidays = new ArrayList<>();
            for (Task task : taskList) {
                ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                        .processInstanceId(task.getProcessInstanceId()).singleResult();
                int hid = Integer.parseInt(processInstance.getBusinessKey());
                Holiday holiday = holidayService.getHolidayByHid(hid);
                holidays.add(holiday);
            }
            for (Holiday holiday: holidays){
                Student_holiday student_holiday = student_holidayService.getStudent_holidayByHid(holiday.getHid());
                student_holidays.add(student_holiday);
            }
            model.addAttribute("student_holidays", student_holidays);
        }
        return "teacher/holidayList";
    }

    /**
     * 单个假条页面
     * @param hid
     * @param model
     * @return
     */
    @RequestMapping("holidayPage")
    public String holidayPage(int hid,Model model){
        Student_holiday student_holiday = student_holidayService.getStudent_holidayByHid(hid);
        model.addAttribute("student_holiday", student_holiday);
        return "teacher/holidayinfo";
    }

    /**
     * 审批假条
     * @param hid
     * @return
     */
    @RequestMapping("approveHoliday")
    public String approveHoliday(int hid){
        holidayService.changeStateByHid(hid);
        return "redirect:teacher/holidayListPage";
    }

    /**
     * 老师所在班级的学生列表
     * @param session
     * @param model
     * @return
     */
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
        return "teacher/scoreStudentList";
    }

    /**
     * 学生的已有成绩列表
     * @param stuid
     * @param model
     * @return
     *
     */
    @RequestMapping("studentScoreListPage")
    public String scoreEntering(int stuid,Model model){

        //根据stuid找到该学生的成绩
        List<Score> scoreList = scoreService.getScoreListByStuid(stuid);
        if (scoreList.size()!=0){
            model.addAttribute("scoreList", scoreList);
        }
        model.addAttribute("stuid", stuid);
        return "teacher/studentScoreList";
    }

    /**
     * 添加一个学生成绩页面
     * @param stuid
     * @param model
     * @return
     */
    @RequestMapping("addScorePage")
    public String addScore(int stuid,Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        Teacher teacher = teacherService.getTeacherByUid(user.getUid());
        Course course = courseService.getCourseByTid(teacher.getTid());
        Score score = new Score();
        score.setStudent(studentService.getStudentByStuid(stuid));
        score.setCourse(course);
        model.addAttribute("score", score);
        return "teacher/addScore";
    }

    /**
     * 添加一个学生成绩
     * @param score
     * @param stuid
     * @param couresid
     * @return
     */
    @RequestMapping("addScore")
    public String addScore(Score score,int stuid,int couresid){
        Student student = studentService.getStudentByStuid(stuid);
        Course course = courseService.getCourseByCourseid(couresid);
        score.setStudent(student);
        score.setCourse(course);
        int i = scoreService.addScore(score);
        return "redirect:teacher/scoreStudentListPage";
    }

    /**
     * 找到本班级各阶段平均成绩
     */
    @RequestMapping("classAverageScore")
    public String classAverageScore(HttpSession session,Model model){
        User user = (User) session.getAttribute("user");
        Teacher teacher = teacherService.getTeacherByUid(user.getUid());
        Classes classes = classesService.getClassesByTid(teacher.getTid());
        List<Integer> datas = new ArrayList<Integer>();
        //根据班级找到学生的成绩
        for (int i = 1;i<6;i++){
            int stage = i;
//            找到各阶段的班级平均分
            int scoreAve = scoreService.getClassAverageScore(classes,stage);
            datas.add(scoreAve);
        }
        ArrayList<String> names = new ArrayList<>();
        names.add("第1阶段");
        names.add("第2阶段");
        names.add("第3阶段");
        names.add("第4阶段");
        names.add("第5阶段");
        model.addAttribute("names", names);
        model.addAttribute("datas", datas);
        //获取班级各阶段的平均分
        return "teacher/classAverageScore";
    }

    /**
     * 学生列表
     */
    @RequestMapping("studentListForScorePage")
    public String studentList(HttpSession session,Model model){
        User user = (User) session.getAttribute("user");
        Teacher teacher = teacherService.getTeacherByUid(user.getUid());
        Classes classes = classesService.getClassesByTid(teacher.getTid());
        List<Student> studentList = studentService.getStudentListByCid(classes.getCid());
        model.addAttribute("studentList", studentList);
        return "teacher/studentListForScore";
    }

    /**
     *
     * 获取学生各个阶段的成绩list
     */
    @RequestMapping("studentScoreList")
    public String studentScoreList(int stuid,Model model){
        List<Integer> scoreList = new ArrayList<Integer>();
        for (int i = 1;i < 6;i++){
            int stage = i;
            Score score = scoreService.getScoreByStuid_stage(stuid,stage);
            if (score != null){
                int j = score.getScore();
                scoreList.add(j);
            }else {
                scoreList.add(0);
            }
        }
        System.out.println("这个学生的成绩列表"+ scoreList.size());
        ArrayList<String> names = new ArrayList<>();
        names.add("第1阶段");
        names.add("第2阶段");
        names.add("第3阶段");
        names.add("第4阶段");
        names.add("第5阶段");
        model.addAttribute("names", names);
        model.addAttribute("datas", scoreList);
        model.addAttribute("stuid", stuid);
        return "teacher/studentScoreList";
    }

    /**
     * 教师请假
     */
    @RequestMapping("teacherAddHolidayPage")
    public String teacherHolidayPage(HttpSession session,Model model){
        User user = (User) session.getAttribute("user");
        Teacher teacher = teacherService.getTeacherByUid(user.getUid());
        model.addAttribute("teacher",teacher);
        return "teacher/teacherAddHoliday";
    }

    /**
     * 发起教师请假
     */
    @RequestMapping("teacherHoliday")
    public String teacherHoliday(Holiday holiday,int tid){
        System.out.println(holiday);
        int days = getDays(holiday.getStartdate(), holiday.getEnddate());
        holiday.setDays(days);
        holidayService.addteacherHoliday(holiday,tid);
        return "redirect:teacher/myHolidayPage";
    }


    /**
     * 根据日期获取天数
     * @param startdate
     * @param enddate
     * @return
     */
    private int getDays(String startdate, String enddate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date start = null;
        Date end = null;
        try {
            start = simpleDateFormat.parse(startdate);
            end = simpleDateFormat.parse(enddate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long daysTime = end.getTime() - start.getTime();
        int days = (int)daysTime/(24*60*60*1000);
        return days;
    }

    /**
     * 老师的假条列表
     */
    @RequestMapping("myHolidayPage")
    public String myHolidayPage(Model model,HttpSession session){
        User user=(User) session.getAttribute("user");
        Teacher teacher = teacherService.getTeacherByUid(user.getUid());
        List<Teacher_holiday> teacher_holidayList = teacher_holidayService.getTeacher_holidayByTid(teacher.getTid());
        List<Holiday> holidays = null;
        if (teacher_holidayList.size() != 0){
            holidays = new ArrayList<>();
            for (Teacher_holiday teacher_holiday:teacher_holidayList){
                holidays.add(teacher_holiday.getHoliday());
            }
        }
        model.addAttribute("holidays", holidays);
        return "teacher/myHolidayList";
    }
}
