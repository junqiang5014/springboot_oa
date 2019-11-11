package com.test.service.reportService.impl;

import com.test.mapper.reportMapper.ReportMapper;
import com.test.pojo.Report;
import com.test.service.reportService.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportMapper reportMapper;

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
