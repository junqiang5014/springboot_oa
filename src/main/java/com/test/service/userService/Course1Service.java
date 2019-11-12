package com.test.service.userService;

import com.test.pojo.Course;

import java.util.List;

public interface Course1Service {

    /**
     * 查询所有的课程
     * @return
     */
    public List<Course> getCourse();


    /**
     * 修改课程的名称
     */
    public int updateCourse(Course course);


    /**
     * 删除课程
     */
    public int deleteCourse(int courseid);

    /**
     * 增加课程
     */
    public int addCourse(Course course);
}
