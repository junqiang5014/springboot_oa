package com.test.service.userService;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    /**
     * 添加员工
     * @param object
     * @return
     */
    public int addEmployee(Object object);

    public int deleteEmployee(Object object);

    public int changeEmployee(Object object);

    public int deleteTeahcerUser(int tid);
    public int deleteClassLeaderUser(int clid);
    public int deleteSchoolManagerUser(int smid);

    public int deleteUser(int uid);

    public Map<String,Object> getEmployee(int[] page);

    public Map<String,Object> getEmployeeByLike(String name,int[]page);

    /**
     * 根据uid来删除员工信息
     */
    public int deleteTeacherByUid(int uid);
    public int deleteClassLeaderByUid(int uid);
    public int deleteSchoolManagerByUid(int uid);
}
