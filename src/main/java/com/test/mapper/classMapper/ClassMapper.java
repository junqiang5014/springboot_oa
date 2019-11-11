package com.test.mapper.classMapper;

import com.test.pojo.Class;

import java.util.List;

public interface ClassMapper {
    public int addClass(Class aclass);//增加一个班级

    public int deleteClass(int cid);//删除一个班级

    public int updateClass(Class aclass);//修改一个班级

    public Class getClassByCid(int cid);//查看一个班级

    public List<Class> getClassList();//获取班级列表
}
