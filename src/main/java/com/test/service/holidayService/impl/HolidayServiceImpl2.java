package com.test.service.holidayService.impl;

import com.test.mapper.ClassLeaderMapper.CLassLeaderMapper;
import com.test.mapper.classLeader_holidayMapper.ClassLeader_holidayMapper;
import com.test.mapper.holidayMapper.HolidayMapper;
import com.test.mapper.teacherMapper.TeacherMapper;
import com.test.pojo.ClassLeader;
import com.test.pojo.Holiday;
import com.test.pojo.Teacher;
import com.test.service.classLeaderService.ClassLeaderService;
import com.test.service.classLeader_holidayService.Impl.ClassLeader_holidayServiceImpl;
import com.test.service.holidayService.HolidayService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HolidayServiceImpl2 implements HolidayService {

    @Autowired
    private CLassLeaderMapper cLassLeaderMapper;
    @Autowired
    private HolidayMapper holidayMapper;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private ClassLeader_holidayMapper classLeader_holidayMapper;

    @Override
    public int addHoliday(Holiday holiday) {
        holidayMapper.addHoliday(holiday);

       return 0;
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
        return null;
    }

    @Override
    public int changeStateByHid(int hid) {
        return 0;
    }

    @Override
    public int addteacherHoliday(Holiday holiday, int id) {
        ClassLeader classLeader = cLassLeaderMapper.getClassLeaderByClid(id);
        Map<String, Object> map = new HashMap<>();
        map.put("teacher_classLeader_name", classLeader.getClname());
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
        return classLeader_holidayMapper.add(id,holiday.getHid());
    }
}
