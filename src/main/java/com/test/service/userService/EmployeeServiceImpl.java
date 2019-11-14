package com.test.service.userService;

import com.github.pagehelper.PageHelper;
import com.test.mapper.userMapper.EmployeeMapper;
import com.test.mapper.userMapper.UsersMapper;
import com.test.pojo.ClassLeader;
import com.test.pojo.SchoolManager;
import com.test.pojo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;


    @Override
    public int addEmployee(Object object) {

        /**
         * 判断object类型进行类型强转
         */
        if(object instanceof Teacher){
            Teacher teacher = (Teacher) object;
            return employeeMapper.addTeacher(teacher);
        }else if (object instanceof ClassLeader){
            ClassLeader classLeader = (ClassLeader) object;
            return employeeMapper.addClassLeader(classLeader);
        }else if(object instanceof SchoolManager) {
            SchoolManager schoolManager = (SchoolManager) object;
            return employeeMapper.addSchoolManager(schoolManager);
        }else {
            System.out.println("类型不匹配");
            return 0;
        }
    }

    @Override
    public int deleteEmployee(Object object) {
        if(object instanceof Teacher){
            Teacher teacher = (Teacher) object;
            int tid = teacher.getTid();
            return employeeMapper.deleteTeacher(tid);
        }else if (object instanceof ClassLeader){
            ClassLeader classLeader = (ClassLeader) object;
            int clid = classLeader.getClid();
            return employeeMapper.deleteClassLeader(clid);
        }else if(object instanceof SchoolManager) {
            SchoolManager schoolManager = (SchoolManager) object;
            int smid = schoolManager.getSmid();
            return employeeMapper.deleteSchoolManager(smid);
        }else {
            System.out.println("类型不匹配");
            return 0;
        }
    }

    @Override
    public int changeEmployee(Object object) {
        if(object instanceof Teacher){
            Teacher teacher = (Teacher) object;
            return employeeMapper.updateTeacher(teacher);
        }else if (object instanceof ClassLeader){
            ClassLeader classLeader = (ClassLeader) object;
            return employeeMapper.updateClassLeader(classLeader);
        }else if(object instanceof SchoolManager) {
            SchoolManager schoolManager = (SchoolManager) object;
            return employeeMapper.updateSchoolManager(schoolManager);
        }else {
            System.out.println("类型不匹配");
            return 0;
        }
    }

    @Override
    public Map<String, Object> getEmployee(int[]page) {

//        List<Object> objectList = new ArrayList<Object>();


        PageHelper.startPage(page[0],3);
        List<Teacher> teacherList = employeeMapper.getTeacher();
        PageHelper.startPage(page[1],3);
        List<ClassLeader> classLeaderList = employeeMapper.getClassLeader();
        PageHelper.startPage(page[2],4);
        List<SchoolManager> schoolManagerList = employeeMapper.getSchoolManager();

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("teacherList",teacherList);
        map.put("classLeaderList",classLeaderList);
        map.put("schoolManagerList",schoolManagerList);

        return map;

//        for(Teacher teacher:teacherList){
//            objectList.add(teacher);
//        }
//        for (ClassLeader classLeader:classLeaderList){
//            objectList.add(classLeader);
//        }
//        for (SchoolManager schoolManager:schoolManagerList){
//            objectList.add(schoolManager);
//        }
//        System.out.println("获取成功");

    }

    @Override
    public Map<String,Object> getEmployeeByLike(String name,int[]page) {
        List<Object> objectList = new ArrayList<Object>();

        PageHelper.startPage(page[0],3);
        List<Teacher> teacherList = employeeMapper.getTeacherByLike(name);
        PageHelper.startPage(page[1],3);
        List<ClassLeader> classLeaderList = employeeMapper.getClassLeaderByLike(name);
        PageHelper.startPage(page[2],3);
        List<SchoolManager> schoolManagerList = employeeMapper.getSchoolManagerByLike(name);

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("teacherList",teacherList);
        map.put("classLeaderList",classLeaderList);
        map.put("schoolManagerList",schoolManagerList);

//        for(Teacher teacher:teacherList){
//            objectList.add(teacher);
//        }
//        for (ClassLeader classLeader:classLeaderList){
//            objectList.add(classLeader);
//        }
//        for (SchoolManager schoolManager:schoolManagerList){
//            objectList.add(schoolManager);
//        }
        System.out.println("模糊查询获取成功");


        return map;

    }



}
