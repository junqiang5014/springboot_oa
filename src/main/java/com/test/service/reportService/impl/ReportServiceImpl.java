package com.test.service.reportService.impl;

import com.test.mapper.reportMapper.ReportMapper;
import com.test.mapper.scoreMapper.ScoreMapper;
import com.test.pojo.Report;
import com.test.pojo.Score;
import com.test.pojo.Student;
import com.test.pojo.Teacher;
import com.test.service.reportService.ReportService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportMapper reportMapper;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private ScoreMapper scoreMapper;


    @Override
    public int addReport(Report report) {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("studentname",report.getStudent().getStuname());
        map.put("teachername","teachername");
        int i = 0;

        i =  reportMapper.addReport(report);

        runtimeService.startProcessInstanceByKey("report",report.getStudent().getStuid()+"",map);

        Task task = taskService.createTaskQuery().taskAssignee(report.getStudent().getStuname()).singleResult();

        taskService.complete(task.getId());

        return i;

    }

    @Override
    public int deleteReportByRid(int rid, Student student) {
        Report report=new Report();
        Teacher teacher=new Teacher();
        Score score = scoreMapper.getScoreByStuid(report.getStudent().getStuid());
        if(score!=null){
            System.out.println("该表不能被删除");
        }
        Task task=taskService.createTaskQuery().taskAssignee(teacher.getTname()).singleResult();
        taskService.deleteTask(task.getId());
        int rid1 = report.getRid();
        int i=reportMapper.deleteReportByRid(rid1);
       
        return i;
    }

    @Override
    public int updateReport(Report report) {
        String rid = String.valueOf(report.getRid());
        String id = taskService.createTaskQuery().processInstanceBusinessKey(rid).singleResult().getId();
        taskService.complete(id);
        return reportMapper.updateReport(report);
    }

    @Override
    public Report getReportByRid(int rid) {
        return reportMapper.getReportByRid(rid);
    }

    @Override
    public List<Report> getReportList() {
        return reportMapper.getReportList();
    }
}
