package com.test.service.teacher_holidayService;

import com.test.pojo.Teacher_holiday;

import java.util.List;

public interface Teacher_holidayService {
    public int add(int tid,int hid);

    public List<Teacher_holiday> getTeacher_holidayByTid(int tid);
}
