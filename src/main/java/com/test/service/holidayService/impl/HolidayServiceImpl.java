package com.test.service.holidayService.impl;

import com.test.mapper.holidayMapper.HolidayMapper;
import com.test.mapper.teacherMapper.TeacherMapper;
import com.test.pojo.Holiday;
import com.test.pojo.Teacher;
import com.test.service.holidayService.HolidayService;
import com.test.service.teacher_holidayService.Teacher_holidayService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HolidayServiceImpl implements HolidayService {

    @Autowired
    private HolidayMapper holidayMapper;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private Teacher_holidayService teacher_holidayService;

    @Override
    public int addHoliday(Holiday holiday) {
        return holidayMapper.addHoliday(holiday);
    }

    @Override
    public int deleteHolidayByHid(int hid) {
        return holidayMapper.deleteHolidayByHid(hid);
    }

    @Override
    public int updateHoliday(Holiday holiday) {
        return holidayMapper.updateHoliday(holiday);
    }

    @Override
    public Holiday getHolidayByHid(int hid) {
        return holidayMapper.getHolidayByHid(hid);
    }

    @Override
    public List<Holiday> getHolidayList() {
        return holidayMapper.getHolidayList();
    }

    @Override
    public int changeStateByHid(int hid) {
        String s = String.valueOf(hid);
        Task task = taskService.createTaskQuery().processInstanceBusinessKey(s).singleResult();
        taskService.complete(task.getId());
        return holidayMapper.changeStateByHid(hid);
    }

    @Override
    public int addteacherHoliday(Holiday holiday, int tid) {
        Teacher teacher = teacherMapper.getTeacherByTid(tid);
        Map<String, Object> map = new HashMap<>();
        map.put("teacher_classLeader_name", teacher.getTname());
        map.put("schoolManagerName", "schoolManager");
//        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("teacher_classLeader_holiday", map);
//        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
//        taskService.complete(task.getId());
//        holidayMapper.addHoliday(holiday);
        runtimeService.startProcessInstanceByKey("teacher_classLeader_holiday", map);
        String bkey = String.valueOf(holidayMapper.addHoliday(holiday));
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(bkey).singleResult();
        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        taskService.complete(task.getId());
        return teacher_holidayService.add(tid,holiday.getHid());
    }




}
