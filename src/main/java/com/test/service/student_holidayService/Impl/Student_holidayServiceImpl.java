package com.test.service.student_holidayService.impl;

import com.test.mapper.student_holidayMapper.Student_holidayMapper;
import com.test.pojo.Student_holiday;
import com.test.service.student_holidayService.Student_holidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Student_holidayServiceImpl implements Student_holidayService {

    @Autowired
    private Student_holidayMapper student_holidayMapper;

    @Override
    public int add(Student_holiday student_holiday) {
        return student_holidayMapper.add(student_holiday);
    }

    @Override
    public List<Student_holiday> getStudent_holidayListByStuid(int stuid) {
        return student_holidayMapper.getStudent_holidayListByStuid(stuid);
    }

    @Override
    public Student_holiday getStudent_holidayByHid(int hid) {
        return student_holidayMapper.getStudent_holidayByHid(hid);
    }

    @Override
    public int addHoliday(int stuid, int hid) {
        return student_holidayMapper.addHoliday(stuid,hid);
    }
}

