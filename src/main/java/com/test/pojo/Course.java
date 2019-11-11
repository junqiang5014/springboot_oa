package com.test.pojo;

public class Course {
    private int courseid;
    private String coursename;
    private Teacher teacher;

    @Override
    public String toString() {
        return "Course{" +
                "courseid=" + courseid +
                ", coursename='" + coursename + '\'' +
                ", teacher=" + teacher +
                '}';
    }

    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
