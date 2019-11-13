package com.test.pojo;

public class Teacher_holiday {
    private Teacher teacher;
    private Holiday holiday;

    @Override
    public String toString() {
        return "Teacher_holiday{" +
                "teacher=" + teacher +
                ", holiday=" + holiday +
                '}';
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Holiday getHoliday() {
        return holiday;
    }

    public void setHoliday(Holiday holiday) {
        this.holiday = holiday;
    }
}
