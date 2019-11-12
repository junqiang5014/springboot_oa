package com.test.mapper.ClassLeaderMapper;

import com.sun.org.apache.bcel.internal.generic.LSTORE;
import com.test.pojo.*;

import java.util.List;

public interface CLassLeaderMapper {

    public List<User> selectUser();
    //班主任查看个人资料
    public ClassLeader selectMessage(int clid);

    //周报查看
    public List<Report> selectReport();

    //周报的筛选查看
    public List<Report> selectReportByStuname(String stuname);

    //学生信息查看
    public List<Student> selectStudent();

    //导入学生信息
    public int insertStudentMessage(Student student);

    //查看单个学生的成绩
    public List<Score> selectScoreByStuname(Student student);

    //查看所有学生的成绩
    public List<Score> selectScore();
}
