package com.test.mapper.SchoolManagerMapper;

import com.test.pojo.AllColumn;
import com.test.pojo.SchoolManager;

import java.io.InputStream;
import java.util.List;

public interface SchoolManagerMapper {

    /**
     * 根据UId查询校长信息
     * @return
     */
    public SchoolManager getSchoolManagerByUid(int uid);

    /**
     * 修改校长
     * @param schoolManager
     * @return
     */
    public int updateSchoolManager(SchoolManager schoolManager);

    //查看班级平均成绩
    public List<AllColumn> selectScoreAvg(String cname);

    //查看学生成绩走势
    public List<AllColumn> selectScoreByStuname(String stuname);

    public List getBankListByExcel(InputStream in, String fileName) throws Exception;


}
