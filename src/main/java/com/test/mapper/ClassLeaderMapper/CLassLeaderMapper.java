package com.test.mapper.ClassLeaderMapper;

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

    //查看班级平均成绩
    public List<AllColumn> selectScoreAvg(String cname);

    //查看学生成绩走势
    public List<AllColumn> selectScoreByStuname(String stuname);
}
