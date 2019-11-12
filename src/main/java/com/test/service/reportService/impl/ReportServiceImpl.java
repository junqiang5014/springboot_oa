package com.test.service.reportService.impl;

import com.test.mapper.reportMapper.ReportMapper;
import com.test.pojo.Report;
import com.test.service.reportService.ReportService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportMapper reportMapper;
    @Autowired
    private TaskService taskService;

    @Override
    public int addReport(Report report) {
        return reportMapper.addReport(report);
    }

    @Override
    public int deleteReportByRid(int rid) {
        return reportMapper.deleteReportByRid(rid);
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
