package com.test.service.courseService;

import com.test.pojo.Course;

import java.util.List;

public interface CourseService {
    public int addCourse(Course course);

    public int deleteCourseByCourseid(int courseid);

    public int updateCourse(Course course);

    public Course getCourseByCourseid(int courseid);

    public List<Course> getCourseList();
}
