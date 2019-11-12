package com.test.service.scoreService.impl;

import com.test.mapper.scoreMapper.ScoreMapper;
import com.test.pojo.Score;
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
}
