package com.test.mapper.student_holidayMapper;

public interface Student_holidayMapper {

    /**
     * 学生发起请假
     * @param stuid
     * @param hid
     * @return
     */
    public int addHoliday(int stuid,int hid);
}
