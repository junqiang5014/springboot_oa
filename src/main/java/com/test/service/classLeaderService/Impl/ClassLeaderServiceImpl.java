package com.test.service.classLeaderService.Impl;

import com.test.mapper.ClassLeaderMapper.CLassLeaderMapper;
import com.test.pojo.*;
import com.test.service.classLeaderService.ClassLeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


  /*  @Override
    public int updateUpwdByClassLeader(User user) {
        return classLeaderMapper.updateUpwdByClassLeader(user);
    }
*/
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


}
//}
