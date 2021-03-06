package com.test.service.classesService.impl;

import com.test.mapper.classesMapper.ClassesMapper;
import com.test.pojo.Classes;
import com.test.service.classesService.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassesServiceImpl implements ClassesService {

    @Autowired
    private ClassesMapper classesMapper;

    @Override
    public int addClasses(Classes classes) {
        return classesMapper.addClasses(classes);
    }

    @Override
    public int deleteClasses(int cid) {
        return classesMapper.deleteClasses(cid);
    }

    @Override
    public int updateClasses(Classes classes) {
        return classesMapper.updateClasses(classes);
    }

    @Override
    public Classes getClassByCid(int cid) {
        return classesMapper.getClassByCid(cid);
    }

    @Override
    public List<Classes> getClassesList() {
        return classesMapper.getClassesList();
    }

    @Override
    public Classes getClassesByTid(int tid) {
        return classesMapper.getClassesByTid(tid);
    }

    @Override
    public Classes getClassByCid2(int cid) {
        return classesMapper.getClassByCid2(cid);
    }
}
