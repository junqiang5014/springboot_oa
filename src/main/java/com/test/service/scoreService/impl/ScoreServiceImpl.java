package com.test.service.scoreService.impl;

import com.test.mapper.scoreMapper.ScoreMapper;
import com.test.mapper.studentMapper.StudentMapper;
import com.test.pojo.Classes;
import com.test.pojo.Course;
import com.test.pojo.Score;
import com.test.pojo.Student;
import com.test.service.scoreService.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public int addScore(Score score) {
        //根据学生id和stage去重
        Student student = score.getStudent();
        Course course = score.getCourse();
        Score s = getScoreByStuid_stage(student.getStuid(), course.getCourseid());
        int i = s.getScore();
        if (i == -1){
            return i;
        }
        return scoreMapper.addScore(score);
    }

    @Override
    public int deleteScoreByScoreid(int scoreid) {
        return scoreMapper.deleteScoreByScoreid(scoreid);
    }

    @Override
    public int updateScore(Score score) {
        return scoreMapper.updateScore(score);
    }

    @Override
    public Score getScoreByScoreid(int scoreid) {
        return scoreMapper.getScoreByScoreid(scoreid);
    }

    @Override
    public List<Score> getScoreList() {
        return scoreMapper.getScoreList();
    }

    @Override
    public List<Score> getScoreListByStuid(int stuid) {
        return scoreMapper.getScoreListByStuid(stuid);
    }

    @Override
    public Score getScoreByStuid_stage(int stuid, int stage) {
        return scoreMapper.getScoreByStuid_stage(stuid,stage);
    }

    @Override
    public int getClassAverageScore(Classes classes, int stage) {
        List<Student> studentList = studentMapper.getStudentListByCid(classes.getCid());
        int sum = 0;
        int count = 0;
        for (Student student:studentList){
            Score score = scoreMapper.getScoreByStuid_stage(student.getStuid(), stage);
            if (score != null){
                int i = score.getScore();
                sum += i;
                count++;
            }
        }
        if (count == 0){
            return 0;
        }
        int a = sum/count;
        return a;
    }

    @Override
    public Score getScoreByStuid(int stuid) {
        return scoreMapper.getScoreByStuid(stuid);
    }
}
