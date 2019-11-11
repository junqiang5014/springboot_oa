package com.test.service.reportService;

import com.test.pojo.Report;

import java.util.List;

public interface ReportService {
    public int addReport(Report report);

    public int deleteReportByRid(int rid);

    public int updateReport(Report report);

    public Report getReportByRid(int rid);

    public List<Report> getReportList();
}
