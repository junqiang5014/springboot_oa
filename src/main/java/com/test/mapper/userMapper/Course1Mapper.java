package com.test.mapper.userMapper;

import com.test.pojo.Course;

import java.util.List;

/**
 * 超级管理员对课程的操作
 */
public interface Course1Mapper {

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
