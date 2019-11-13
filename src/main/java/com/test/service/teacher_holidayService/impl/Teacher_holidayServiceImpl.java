package com.test.service.teacher_holidayService.impl;

import com.test.mapper.teacher_holidayMapper.Teacher_holidayMapper;
import com.test.pojo.Teacher_holiday;
import com.test.service.teacher_holidayService.Teacher_holidayService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Teacher_holidayServiceImpl implements Teacher_holidayService {

    @Autowired
    private Teacher_holidayMapper teacher_holidayMapper;

    @Override
    public int add(int tid, int hid) {
        return teacher_holidayMapper.add(tid,hid);
    }

    @Override
    public List<Teacher_holiday> getTeacher_holidayByTid(int tid) {
        return teacher_holidayMapper.getTeacher_holidayByTid(tid);
    }
}
