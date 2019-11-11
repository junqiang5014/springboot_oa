package com.test.service.teacherService.impl;

import com.test.mapper.teacherMapper.TeacherMapper;
import com.test.pojo.Teacher;
import com.test.service.teacherService.TeacherService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Qualifier
    public TeacherMapper teacherMapper;

    @Override
    public int addTeacher(Teacher teacher) {
        return teacherMapper.addTeacher(teacher);
    }

    @Override
    public int deleteTeacher(int tid) {
        return teacherMapper.deleteTeacher(tid);
    }

    @Override
    public int updateTeacher(Teacher teacher) {
        return teacherMapper.updateTeacher(teacher);
    }

    @Override
    public Teacher getTeacherByTid(int tid) {
        return teacherMapper.getTeacherByTid(tid);
    }

    @Override
    public List<Teacher> getTeacherList() {
        return teacherMapper.getTeacherList();
    }
}
