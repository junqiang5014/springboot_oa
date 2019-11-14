package com.test.mapper.SchoolManagerMapper;

import com.test.pojo.SchoolManager;

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


}
