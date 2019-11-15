package com.test.mapper.userMapper;

import com.test.pojo.ClassLeader;
import com.test.pojo.SchoolManager;
import com.test.pojo.Teacher;

import java.util.List;

public interface EmployeeMapper {

    /**
     * 查找所有员工 即
     * 查找所有的讲师,班主任以及校长
     */

    public List<Teacher> getTeacher();
    public List<ClassLeader> getClassLeader();
    public List<SchoolManager> getSchoolManager();


    /**
     * 删除员工信息
     * @param tid
     * @return
     */
    public int deleteTeacher(int tid);
    public int deleteClassLeader(int clid);
    public int deleteSchoolManager(int smid);

    /**
     * 模糊查询员工信息
     * @return
     */
    public List<Teacher> getTeacherByLike(String tname);
    public List<ClassLeader> getClassLeaderByLike(String clname);
    public List<SchoolManager> getSchoolManagerByLike(String smname);


    /**
     * 修改员工信息
     */
    public int updateTeacher(Teacher teacher);
    public int updateClassLeader(ClassLeader classLeader);
    public int updateSchoolManager(SchoolManager schoolManager);


    /**
     * 添加员工信息
     */
    public int addTeacher(Teacher teacher);
    public int addClassLeader(ClassLeader classLeader);
    public int addSchoolManager(SchoolManager schoolManager);

    /**
     * 根据员工id来查询uid
     */
    public int getTeacherUid(int tid);
    public int getClassLeaderUid(int clid);
    public int getSchoolManagerUid(int smid);

    /**
     * 根据uid来删除user账户信息
     */

    public int deleteUser(int uid);

    /**
     * 根据uid来删除员工信息
     */
    public int deleteTeacherByUid(int uid);
    public int deleteClassLeaderByUid(int uid);
    public int deleteSchoolManagerByUid(int uid);

}
