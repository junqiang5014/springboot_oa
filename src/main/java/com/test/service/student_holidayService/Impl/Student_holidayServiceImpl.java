package com.test.service.student_holidayService.impl;

import com.test.mapper.student_holidayMapper.Student_holidayMapper;
import com.test.pojo.Holiday;
import com.test.pojo.Student_holiday;
import com.test.service.student_holidayService.Student_holidayService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Student_holidayServiceImpl implements Student_holidayService {
//
    @Autowired
    private Student_holidayMapper student_holidayMapper;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;


    public Student_holidayMapper getStudent_holidayMapper() {
        return student_holidayMapper;
    }

    public void setStudent_holidayMapper(Student_holidayMapper student_holidayMapper) {
        this.student_holidayMapper = student_holidayMapper;
    }

    public RuntimeService getRuntimeService() {
        return runtimeService;
    }

    public void setRuntimeService(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    @Override
    public int add(Student_holiday student_holiday) {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("studentname",student_holiday.getStudent().getStuname());
        map.put("teachername","teachername");
        map.put("classleadername","classleadername");
        map.put("schoolmanagername","schoolmanagername");
        map.put("days",3);
        int i = 0;

        i =  student_holidayMapper.add(student_holiday);

        runtimeService.startProcessInstanceByKey("student_holiday",student_holiday.getHoliday().getHid()+"",map);

        Task task = taskService.createTaskQuery().taskAssignee(student_holiday.getStudent().getStuname()).singleResult();

        taskService.complete(task.getId());

        return i;

    }

    /**
     * 根据日期获取天数
     * @param startDate
     * @param endDate
     * @return
     */
    private int getDays(String startDate, String endDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date start = null;
        Date end = null;
        try {
            start = simpleDateFormat.parse(startDate);
            end = simpleDateFormat.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long daysTime = end.getTime() - start.getTime();
        int days = (int)daysTime/(24*60*60*1000);
        return days;
    }


    @Override
    public List<Student_holiday> getStudent_holidayListByStuid(int stuid) {
        return student_holidayMapper.getStudent_holidayListByStuid(stuid);
    }

    @Override
    public Student_holiday getStudent_holidayByHid(int hid) {
        return student_holidayMapper.getStudent_holidayByHid(hid);
    }

    @Override
    public int addHoliday(int stuid, int hid) {

        return student_holidayMapper.addHoliday(stuid,hid);
    }


}
