package com.test.controller.schoolmanagerController;

import com.test.pojo.*;
import com.test.service.holidayService.HolidayService;
import com.test.service.schoolmanagerService.SchoolManagerService;
import com.test.service.teacher_holidayService.Teacher_holidayService;
import com.test.service.userService.UsersService;
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

@Controller
public class SchoolManagerController {
    @Autowired
    private SchoolManagerService schoolManagerService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private HolidayService holidayService;
    @Autowired
    private Teacher_holidayService teacher_holidayService;

    /**
     * 跳转到修改密码页面
     * @param session
     * @return
     */
    @RequestMapping("editUpwd2")
    public String updateUpwd(HttpSession session,Model model){
        User user = (User) session.getAttribute("user");
        SchoolManager schoolManager = schoolManagerService.getSchoolManagerByUid(user.getUid());
        model.addAttribute("schoolManager",schoolManager);
        return "redirect:updateUpwd2";
    }

    /**
     * 修改密码
     * @param upwd
     * @param newPwd
     * @param cfgPwd
     * @return
     */
    @RequestMapping("updateUpwd2")
    public String editUpwd(String upwd, String newPwd, String cfgPwd){
        int i = usersService.updateUserPwd(upwd,newPwd,cfgPwd);
        if(i>0){
            return "redirect:index";
        }
        return "redirect:editUpwd2";

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
        SchoolManager schoolManager = schoolManagerService.getSchoolManagerByUid(user.getUid());
        List<Task> taskList = taskService.createTaskQuery().taskAssignee(schoolManager.getSmname()).list();
        if (taskList.size() != 0) {
            ArrayList<Holiday> holidays = new ArrayList<>();
            List<Teacher_holiday> teacher_holidays = new ArrayList<>();
            for (Task task : taskList) {
                ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                        .processInstanceId(task.getProcessInstanceId()).singleResult();
                int hid = Integer.parseInt(processInstance.getBusinessKey());
                Holiday holiday = holidayService.getHolidayByHid(hid);
                holidays.add(holiday);
            }
            for (Holiday holiday: holidays){
                Teacher_holiday teacher_holiday= teacher_holidayService.getTeacher_holidayByHid(holiday.getHid());
                teacher_holidays.add(teacher_holiday);
            }
            model.addAttribute("teacher_holidays", teacher_holidays);
        }
        return "schoolmanager/holidayList";
    }

    /**
     * 审批假条
     * @param hid
     * @return
     */
    @RequestMapping("approveHoliday")
    public String approveHoliday(int hid){
        holidayService.changeStateByHid(hid);
        return "redirect:schoolmanager/holidayListPage";
    }


}
