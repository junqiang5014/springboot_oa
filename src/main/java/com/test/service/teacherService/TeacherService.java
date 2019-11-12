package com.test.service.teacherService;

import com.test.pojo.Student;
import com.test.pojo.Teacher;

import java.util.List;

public interface TeacherService {
    public int addTeacher(Teacher teacher);//增加一个老师

    public int deleteTeacher(int tid);//删除一个老师

    public int updateTeacher(Teacher teacher);//修改老师信息

    public Teacher getTeacherByTid(int tid);//查找一个老师

    public List<Teacher> getTeacherList();//获取老师列表

    public Teacher getTeacherByUid(int uid);//根据uid获取老师对象

    public List<Student> getStudentListByTid(int tid);//找到该老师所在班级的学生列表
}
