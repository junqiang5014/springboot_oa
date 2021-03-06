package com.test.service.student_holidayService;

import com.test.pojo.Student_holiday;

import java.util.List;

public interface Student_holidayService {
    public int add(Student_holiday student_holiday);
    public List<Student_holiday> getStudent_holidayListByStuid(int stuid);

    public Student_holiday getStudent_holidayByHid(int hid);


    /**
     * 学生发起请假
     * @param stuid
     * @param hid
     * @return
     */
    public int addHoliday(int stuid,int hid);
}
