package com.test.service.studentService;

import com.test.pojo.Student;
import com.test.pojo.User;

import java.util.List;

public interface StudentService {

//
    /**
     * 修改学生个人信息
     * @param student
     * @return
     *
     */
    public int updateStudentByUser(Student student, User user);

    /**
     * 根据角色查询学生信息
     * @param uid
     * @return
     */
    public Student getStudentByUid(int uid);

    public Student getStudentByUser(User user);

    public Student getStudentByStuid(int stuid);

    public List<Student> getStudentAndClassList();

    //根据cid找到学生列表
    public List<Student> getStudentListByCid(int cid);
}


