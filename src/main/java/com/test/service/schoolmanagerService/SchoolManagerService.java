package com.test.service.schoolmanagerService;

import com.test.pojo.SchoolManager;

public interface SchoolManagerService {

    /**
     * 根据UId查询校长信息
     * @return
     */
    public SchoolManager getSchoolManagerByUid(int uid);
}
