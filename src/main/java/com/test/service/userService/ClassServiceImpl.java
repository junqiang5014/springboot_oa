package com.test.service.userService;

import com.test.mapper.userMapper.ClassMapper;
import com.test.pojo.Classes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService{

    @Autowired
    private ClassMapper classMapper;

    @Override
    public List<Classes> getAllClass() {
        return classMapper.getAllClass();
    }

    @Override
    public int deleteClass(int cid) {
        return classMapper.deleteClass(cid);
    }

    @Override
    public int addClass(Classes classes) {
        return classMapper.addClass(classes);
    }

    @Override
    public int updateClass(Classes classes) {
        return classMapper.updateClass(classes);
    }

    @Override
    public int getTid(String tname) {
        return classMapper.getTid(tname);
    }

    @Override
    public int getClid(String clname) {
        return classMapper.getClid(clname);
    }

    @Override
    public Classes getClassByid(int cid) {
        return classMapper.getClassByid(cid);
    }
}
