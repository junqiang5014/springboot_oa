package com.test.mapper.scoreMapper;

import com.test.pojo.Score;

import java.util.List;

public interface ScoreMapper {
    public int addScore(Score score);

    public int deleteScoreByScoreid(int scoreid);

    public int updateScore(Score score);

    public Score getScoreByScoreid(int scoreid);

    public List<Score> getScoreList();
}
