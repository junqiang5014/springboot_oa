package com.test.mapper.scoreMapper;

import com.test.pojo.Score;

import java.util.List;

public interface ScoreMapper {
    public int addScore(Score score);

    public int deleteScoreByScoreid(int scoreid);

    public int updateScore(Score score);

    public Score getScoreByScoreid(int scoreid);

    public List<Score> getScoreList();

    public List<Score> getScoreListByStuid(int stuid);

    public int getScoreByStuid_stage(int stuid, int stage);//根据stuid和stage去重复

    public List<List> getClassesScoreAVG(String className);//获取本班级各阶段平均成绩对比

    /**
     * 根据stuid查询出周报分数
     * @param stuid
     * @return
     */
    public List<Score> getScoreByStuid(int stuid);
}
