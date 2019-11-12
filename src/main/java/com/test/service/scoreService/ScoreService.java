package com.test.service.scoreService;

import com.test.pojo.Score;

import java.util.List;

public interface ScoreService {
    public int addScore(Score score);

    public int deleteScoreByScoreid(int scoreid);

    public int updateScore(Score score);

    public Score getScoreByScoreid(int scoreid);

    public List<Score> getScoreList();

    public List<Score> getScoreListByStuid(int stuid);

    public List<Score> getScoreListByStuid_stage(int stuid, int stage);//根据stuid和stage去重复
}
