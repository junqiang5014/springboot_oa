package com.test.service.schoolmanagerService.impl;

import com.test.mapper.SchoolManagerMapper.SchoolManagerMapper;
import com.test.pojo.SchoolManager;
import com.test.service.schoolmanagerService.SchoolManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolManagerServiceImpl implements SchoolManagerService {
    @Autowired
    private SchoolManagerMapper schoolManagerMapper;

    public SchoolManagerMapper getSchoolManagerMapper() {
        return schoolManagerMapper;
    }

    public void setSchoolManagerMapper(SchoolManagerMapper schoolManagerMapper) {
        this.schoolManagerMapper = schoolManagerMapper;
    }

    @Override
    public SchoolManager getSchoolManagerByUid(int uid) {
        return schoolManagerMapper.getSchoolManagerByUid(uid);
    }

    @Override
    public int updateSchoolManager(SchoolManager schoolManager) {
        return schoolManagerMapper.updateSchoolManager(schoolManager);
    }
}
