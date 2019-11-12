package com.test.service.classesService;

import com.test.pojo.Classes;

import java.util.List;

public interface ClassesService {
    public int addClasses(Classes classes);//增加一个班级

    public int deleteClasses(int cid);//删除一个班级

    public int updateClasses(Classes classes);//修改一个班级

    public Classes getClassByCid(int cid);//查看一个班级

    public List<Classes> getClassesList();//获取班级列表

    public Classes getClassesByTid(int tid);//根据tid找到班级对象
}
