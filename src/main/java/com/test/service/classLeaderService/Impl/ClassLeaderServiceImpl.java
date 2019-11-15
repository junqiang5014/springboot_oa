package com.test.service.classLeaderService.Impl;

import com.test.mapper.ClassLeaderMapper.CLassLeaderMapper;
import com.test.pojo.*;
import com.test.service.classLeaderService.ClassLeaderService;
import javafx.scene.control.Cell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClassLeaderServiceImpl implements ClassLeaderService {

    public CLassLeaderMapper getcLassLeaderMapper() {
        return classLeaderMapper;
    }

    public void setcLassLeaderMapper(CLassLeaderMapper cLassLeaderMapper) {
        this.classLeaderMapper = cLassLeaderMapper;
    }

    @Autowired
    private CLassLeaderMapper classLeaderMapper;


   @Override
    public int updateUpwdByClassLeader(User user) {
        return classLeaderMapper.updateUpwdByClassLeader(user);
    }

    @Override
    public ClassLeader selectMessage(int clid) {
        return classLeaderMapper.selectMessage(clid);
    }

    @Override
    public List<Report> selectReport() {
        return classLeaderMapper.selectReport();
    }

    @Override
    public List<Report> selectReportByStuname(String stuname) {
        return classLeaderMapper.selectReportByStuname(stuname);
    }

    @Override
    public List<Student> selectStudent() {
        return classLeaderMapper.selectStudent();

    }
//package com.test.service.classLeaderService.Impl;
//
//import com.test.mapper.ClassLeaderMapper.CLassLeaderMapper;
//import com.test.pojo.*;
//import com.test.service.classLeaderService.ClassLeaderService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ClassLeaderServiceImpl implements ClassLeaderService {
//
//    public CLassLeaderMapper getcLassLeaderMapper() {
//        return classLeaderMapper;
//    }
//
//    public void setcLassLeaderMapper(CLassLeaderMapper cLassLeaderMapper) {
//        this.classLeaderMapper = cLassLeaderMapper;
//    }
//
//    @Autowired
//    private CLassLeaderMapper classLeaderMapper;
//
//    @Override
//    public List<User> selectUser() {
//        return classLeaderMapper.selectUser();
//    }
//
//    @Override
//    public ClassLeader selectMessage(int clid) {
//        return classLeaderMapper.selectMessage(clid);
//    }
//
//    @Override
//    public List<Report> selectReport() {
//        return classLeaderMapper.selectReport();
//    }
//
//    @Override
//    public List<Report> selectReportByStuname(String stuname) {
//        return classLeaderMapper.selectReportByStuname(stuname);
//    }
//
//    @Override
//    public List<Student> selectStudent() {
//        return classLeaderMapper.selectStudent();
//
//    }
//@Configuration
//public class ClassLeaderServiceImpl {

    @Override
    public int insertStudentMessage(Student student) {
        return classLeaderMapper.insertStudentMessage(student);
    }

    @Override
    public List<Student> export() {
        return classLeaderMapper.export();
    }

//    public CLassLeaderMapper getcLassLeaderMapper() {
//        return classLeaderMapper;
//    }
//
//    public void setcLassLeaderMapper(CLassLeaderMapper cLassLeaderMapper) {
//        this.classLeaderMapper = cLassLeaderMapper;
//    }
//
//    @Autowired
//    private CLassLeaderMapper classLeaderMapper;
//
//    @Override
//    public ClassLeader selectMessage(int clid) {
//        return classLeaderMapper.selectMessage(clid);
//    }
//
//    @Override
//    public List<Report> selectReport() {
//        return classLeaderMapper.selectReport();
//    }
//
//    @Override
//    public List<Report> selectReportByStuname(String stuname) {
//        return classLeaderMapper.selectReportByStuname(stuname);
//    }
//
//    @Override
//    public List<Student> selectStudent() {
//        return classLeaderMapper.selectStudent();
//
//    }
//
//    @Override
//    public int insertStudentMessage(Student student) {
//        return classLeaderMapper.insertStudentMessage(student);
//    }
//
//    @Override
//    public List<Score> selectScoreByStuname(Student student) {
//        return classLeaderMapper.selectScoreByStuname(student);
//    }
//
//    @Override
//    public List<Score> selectScore() {
//        return classLeaderMapper.selectScore();
//    }
//    @Override
//    public int insertStudentMessage(Student student) {
//        return classLeaderMapper.insertStudentMessage(student);
//    }
//
//
//    @Override
//    public List<Score> selectScoreAvg(String cname) {
//        return classLeaderMapper.selectScoreAvg(cname);
//    }
//
//    @Override
//    public Score selectScoreByStuname(String stuname) {
//        return classLeaderMapper.selectScoreByStuname(stuname);
//    }

    @Override
    public List<AllColumn> selectScoreAvg(String cname) {
        return classLeaderMapper.selectScoreAvg(cname);
    }

    @Override
    public List<AllColumn> selectScoreByStuname(String stuname) {
        return classLeaderMapper.selectScoreByStuname(stuname);
    }

    @Override
    public List getBankListByExcel(InputStream in, String fileName) throws Exception {
        List list = new ArrayList<>();
        //创建Excel工作薄
        Workbook work = this.getWorkbook(in, fileName);
        if (null == work) {
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;

        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if (sheet == null) {
                continue;
            }

            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                row = sheet.getRow(j);
                if (row == null || row.getFirstCellNum() == j) {
                    continue;
                }

                List<Object> li = new ArrayList<>();
                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                    cell = (Cell) row.getCell(y);
                    li.add(cell);
                }
                list.add(li);
            }
        }
        work.close();
        return list;
    }

    /**
     * 判断文件格式
     *
     * @param inStr
     * @param fileName
     * @return
     * @throws Exception
     */
    public Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
        Workbook workbook = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (".xls".equals(fileType)) {
            workbook = new HSSFWorkbook(inStr);
        } else if (".xlsx".equals(fileType)) {
            workbook = new XSSFWorkbook(inStr);
        } else {
            throw new Exception("请上传excel文件！");
        }
        return workbook;
    }


}
//}
