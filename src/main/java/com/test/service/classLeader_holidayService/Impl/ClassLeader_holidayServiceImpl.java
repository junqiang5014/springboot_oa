package com.test.service.classLeader_holidayService.Impl;

import com.test.mapper.ClassLeaderMapper.CLassLeaderMapper;
import com.test.mapper.classLeader_holidayMapper.ClassLeader_holidayMapper;
import com.test.pojo.ClassLeader_holiday;
import com.test.service.classLeader_holidayService.classLeader_holidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassLeader_holidayServiceImpl implements classLeader_holidayService {

    @Autowired
    private ClassLeader_holidayMapper classLeader_holidayMapper;

    @Override
    public int add(int clid, int hid) {
        return classLeader_holidayMapper.add(clid,hid);
    }

    @Override
    public List<ClassLeader_holiday> getClassLeader_holidayByclid(int clid) {
        return classLeader_holidayMapper.getClassLeader_holidayByclid(clid);
    }

    @Override
    public ClassLeader_holiday getClassLeader_holidayByHid(int hid) {
        return classLeader_holidayMapper.getClassLeader_holidayByHid(hid);
    }
}
