package com.test.service.studentService;

import com.test.pojo.Student;
import com.test.pojo.User;

public interface StudentService {


    /**
     * 修改学生个人信息
     * @param student
     * @return
     */
    public int updateStudentByUser(Student student, User user);

    /**
     * 根据角色查询学生信息
     * @param user
     * @return
     */
    public Student getStudentByUser(User user);
}
