package com.test.service.classLeaderService;

import com.test.pojo.ClassLeader;
import com.test.pojo.Report;
import com.test.pojo.Score;
import com.test.pojo.Student;

import java.util.List;

public interface ClassLeaderService {
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
