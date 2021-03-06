package com.test.service.studentService.impl;

import com.test.mapper.studentMapper.StudentMapper;
import com.test.pojo.Student;
import com.test.pojo.User;
import com.test.service.studentService.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {



    @Autowired
    private StudentMapper studentMapper;

    public StudentMapper getStudentMapper() {
        return studentMapper;
    }

    public void setStudentMapper(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @Override
    public int updateStudentByUser(Student student, User user) {
        Student student1=studentMapper.getStudentByUser(user);
        student.setStuid(student1.getStuid());
        return studentMapper.updateStudentByUser(student);
    }

    @Override
    public Student getStudentByUid(int uid) {
        return studentMapper.getStudentByUid(uid);
    }

    @Override
    public Student getStudentByUser(User user) {
        return studentMapper.getStudentByUser(user);
    }

    @Override
    public Student getStudentByStuid(int stuid) {
        return studentMapper.getStudentByStuid(stuid);
    }

    @Override
    public List<Student> getStudentAndClassList() {
        return studentMapper.getStudentAndClassList();
    }

    @Override
    public List<Student> getStudentListByCid(int cid) {
        return studentMapper.getStudentListByCid(cid);
    }
}
