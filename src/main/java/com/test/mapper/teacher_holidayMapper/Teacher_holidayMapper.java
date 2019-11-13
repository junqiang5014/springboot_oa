package com.test.mapper.teacher_holidayMapper;

import com.test.pojo.Teacher_holiday;

import java.util.List;

public interface Teacher_holidayMapper {
    public int add(int tid,int hid);

    public List<Teacher_holiday> getTeacher_holidayByTid(int tid);
}
