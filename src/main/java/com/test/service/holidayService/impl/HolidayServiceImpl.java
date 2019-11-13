package com.test.service.holidayService.impl;

import com.test.mapper.holidayMapper.HolidayMapper;
import com.test.mapper.teacherMapper.TeacherMapper;
import com.test.pojo.Holiday;
import com.test.pojo.Teacher;
import com.test.service.holidayService.HolidayService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
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
    public int addHoliday(Holiday holiday, int id) {
        Teacher teacher = teacherMapper.getTeacherByTid(id);
        Map<String, Object> map = new HashMap<>();
        map.put("teachername", teacher.getTname());
        map.put("schoolManagerName", "schoolManager");
        runtimeService.startProcessInstanceByKey("teacher_classLeader_holiday",map);
        return holidayMapper.addHoliday(holiday);
    }
}
