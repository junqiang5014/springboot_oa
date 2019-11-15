package com.test.service.classLeader_holidayService;

import com.test.pojo.ClassLeader_holiday;


import java.util.List;

public interface classLeader_holidayService {
    public int add(int clid,int hid);

    public List<ClassLeader_holiday> getClassLeader_holidayByclid(int clid);

    public ClassLeader_holiday getClassLeader_holidayByHid(int hid);
}
