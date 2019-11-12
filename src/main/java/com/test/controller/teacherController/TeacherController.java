package com.test.controller.teacherController;

import com.test.pojo.Holiday;
import com.test.pojo.Report;
import com.test.pojo.Teacher;
import com.test.pojo.User;
import com.test.service.holidayService.HolidayService;
import com.test.service.reportService.ReportService;
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
import java.awt.event.HierarchyListener;
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
    private RuntimeService runtimeService;
    @Autowired
    private HolidayService holidayService;

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

}