package com.test.mapper.studentMapper;

import com.test.pojo.Student;
import com.test.pojo.User;

import java.util.List;

public interface StudentMapper {

    public int updateStudentByUser(Student student);

    /**
     * 根据角色查询出学生信息
     * @param user
     * @return
     */
    public Student getStudentByUser(User user);

    /**
     * 查询学生班级列表
     * @return
     */
    public List<Student> getStudentAndClassList();

    public int updateStudent(Student student);

    /**
     * 根据stuid查找学生
     * @param stuid
     * @return
     */
    public Student getStudentByStuid(int stuid);

    /**
     * 根据uid查询学生对象
     * @param uid
     * @return
     */
    public Student getStudentByUid(int uid);

    //根据cid找到学生列表
    public List<Student> getStudentListByCid(int cid);

}
