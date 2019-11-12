package com.test.service.studentService.impl;

import com.test.mapper.studentMapper.ClassMapper;
import com.test.service.studentService.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassMapper classMapper;

    public ClassMapper getClassMapper() {
        return classMapper;
    }

    public void setClassMapper(ClassMapper classMapper) {
        this.classMapper = classMapper;
    }

    @Override
    public Class getClassByCid(int cid) {
        return classMapper.getClassByCid(cid);
    }
}
