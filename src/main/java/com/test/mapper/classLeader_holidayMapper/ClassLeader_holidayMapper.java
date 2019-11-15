package com.test.mapper.classLeader_holidayMapper;

import com.test.pojo.ClassLeader;
import com.test.pojo.ClassLeader_holiday;
import com.test.pojo.Teacher;
import com.test.pojo.Teacher_holiday;

import java.util.List;

public interface ClassLeader_holidayMapper {
    public int add(int tid,int hid);

    public List<ClassLeader_holiday> getClassLeader_holidayByclid(int clid);

    public ClassLeader_holiday getClassLeader_holidayByHid(int hid);

    public ClassLeader getClassLeaderByHid(int hid);
}
