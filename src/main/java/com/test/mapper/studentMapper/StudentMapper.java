package com.test.mapper.studentMapper;

import com.test.pojo.Student;
import com.test.pojo.User;

public interface StudentMapper {

    /**
     * 修改学生个人信息
     * @param student
     * @return
     */
    public int updateStudentByUser(Student student);

    /**
     * 根据角色查询出学生信息
     * @param user
     * @return
     */
    public Student getStudentByUser(User user);
}
