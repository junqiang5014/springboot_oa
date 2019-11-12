package com.test.mapper.studentMapper;

import com.test.pojo.Report;

public interface ReportMapper {

    /**
     * 添加周报
     * @param report
     * @return
     */
    public int saveReport(Report report);

    /**
     * 修改周报
     * @param report
     * @return
     */
    public int updateReport(Report report);
}
