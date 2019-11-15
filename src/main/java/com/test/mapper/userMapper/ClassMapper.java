package com.test.mapper.userMapper;

import com.test.pojo.Classes;

import java.util.List;

public interface ClassMapper {

    /**
     * 查询所有班级信息
     */
    public List<Classes> getAllClass();

    /**
     * 删除班级信息
     */
    public int deleteClass(int cid);

    /**
     * 添加班级信息,可直接添加老师和班主任(也可暂不分配)
     */
    public int addClass(Classes classes);

    /**
     * 修改班级信息,可修改班级的老师班主任
     */
    public int updateClass(Classes classes);

    /**
     * 根据tname 查询tid
     */
    public int getTid(String tname);

    /**
     * 根据clname查询clid
     */
    public int getClid(String clname);


    public Classes getClassByid(int cid);
}
