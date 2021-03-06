package com.test.service.scoreService;

import com.test.pojo.Classes;
import com.test.pojo.Score;

import java.util.List;

public interface ScoreService {
    public int addScore(Score score);

    public int deleteScoreByScoreid(int scoreid);

    public int updateScore(Score score);

    public Score getScoreByScoreid(int scoreid);

    public List<Score> getScoreList();

    public List<Score> getScoreListByStuid(int stuid);

    public Score getScoreByStuid_stage(int stuid, int stage);//根据stuid和stage去重复

    public int getClassAverageScore(Classes classes, int stage);

    /**
     * 根据stuid查询出周报分数
     * @param stuid
     * @return
     */
    public Score getScoreByStuid(int stuid);
}
