package com.test.service.userService;

import com.test.mapper.userMapper.Course1Mapper;
import com.test.pojo.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Course1ServiceImpl implements Course1Service {

    @Autowired
    private Course1Mapper courseMapper;

    @Override
    public List<Course> getCourse() {
        return courseMapper.getCourse();
    }

    @Override
    public int updateCourse(Course course) {
        return courseMapper.updateCourse(course);
    }

    @Override
    public int deleteCourse(int courseid) {
        return courseMapper.deleteCourse(courseid);
    }

    @Override
    public int addCourse(Course course) {
        return courseMapper.addCourse(course);
    }
}
