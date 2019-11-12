package com.test.service.scoreService.impl;

import com.test.mapper.scoreMapper.ScoreMapper;
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

    @Override
    public int addScore(Score score) {
        //根据学生id和stage去重
        Student student = score.getStudent();
        Course course = score.getCourse();
        List<Score> list = getScoreListByStuid_stage(student.getStuid(), course.getCourseid());
        if (list.size() != 0){
            return 0;
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
    public List<Score> getScoreListByStuid_stage(int stuid, int stage) {
        return scoreMapper.getScoreListByStuid_stage(stuid,stage);
    }
}
