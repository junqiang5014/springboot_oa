package com.test.service.courseService.impl;

import com.test.mapper.courseMapper.CourseMapper;
import com.test.pojo.Course;
import com.test.service.courseService.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public int addCourse(Course course) {
        return courseMapper.addCourse(course);
    }

    @Override
    public int deleteCourseByCourseid(int courseid) {
        return courseMapper.deleteCourseByCourseid(courseid);
    }

    @Override
    public int updateCourse(Course course) {
        return courseMapper.updateCourse(course);
    }

    @Override
    public Course getCourseByCourseid(int courseid) {
        return courseMapper.getCourseByCourseid(courseid);
    }

    @Override
    public List<Course> getCourseList() {
        return courseMapper.getCourseList();
    }
}
