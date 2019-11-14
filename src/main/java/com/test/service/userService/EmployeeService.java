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

    public Map<String,Object> getEmployee(int[] page);

    public Map<String,Object> getEmployeeByLike(String name,int[]page);

}
