package com.test.mapper.teacherMapper;

import com.test.pojo.Teacher;

import java.util.List;

public interface TeacherMapper {

    public int addTeacher(Teacher teacher);//增加一个老师

    public int deleteTeacher(int tid);//删除一个老师

    public int updateTeacher(Teacher teacher);//修改老师信息

    public Teacher getTeacherByTid(int tid);//查找一个老师

    public Teacher getTeacherByUid(int uid);//根据uid获取老师对象

    public List<Teacher> getTeacherList();//获取老师列表
}
