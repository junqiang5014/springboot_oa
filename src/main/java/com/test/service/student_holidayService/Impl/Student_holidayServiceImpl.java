package com.test.service.student_holidayService.Impl;

import com.test.mapper.student_holidayMapper.Student_holidayMapper;
import com.test.service.student_holidayService.Student_holidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Student_holidayServiceImpl implements Student_holidayService {
    @Autowired
    private Student_holidayMapper student_holidayMapper;

    public Student_holidayMapper getStudent_holidayMapper() {
        return student_holidayMapper;
    }

    public void setStudent_holidayMapper(Student_holidayMapper student_holidayMapper) {
        this.student_holidayMapper = student_holidayMapper;
    }

    @Override
    public int addHoliday(int stuid, int hid) {
        return student_holidayMapper.addHoliday(stuid,hid);
    }
}
