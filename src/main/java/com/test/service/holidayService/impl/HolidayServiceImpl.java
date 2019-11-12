package com.test.service.holidayService.impl;

import com.test.mapper.holidayMapper.HolidayMapper;
import com.test.pojo.Holiday;
import com.test.service.holidayService.HolidayService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HolidayServiceImpl implements HolidayService {

    @Autowired
    private HolidayMapper holidayMapper;
    @Autowired
    private TaskService taskService;

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
}
