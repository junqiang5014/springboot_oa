package com.test.pojo;

public class Student_holiday {
    private Student student;
    private Holiday holiday;

    @Override
    public String toString() {
        return "Student_holiday{" +
                "student=" + student +
                ", holiday=" + holiday +
                '}';
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Holiday getHoliday() {
        return holiday;
    }

    public void setHoliday(Holiday holiday) {
        this.holiday = holiday;
    }
}
