package com.test.service.reportService;

import com.test.pojo.Report;
import com.test.pojo.Student;

import java.util.List;

public interface ReportService {
    public int addReport(Report report);

    public int deleteReportByRid(int rid, Student student);

    public int updateReport(Report report);

    public Report getReportByRid(int rid);

    public List<Report> getReportList();
}
