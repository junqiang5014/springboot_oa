
package com.test.controller.classLeaderController;


import com.test.pojo.*;
import com.test.service.classLeaderService.ClassLeaderService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ClassLeaderController {
    @Autowired
    private ClassLeaderService classLeaderService;
    @Autowired
    private SecurityManager securityManager;

    @RequestMapping("selectMessage")
    public String selectMessage(Model model){
         ClassLeader classLeader= classLeaderService.selectMessage(1);
        model.addAttribute("classLeader",classLeader);
        return "viewstu";
    }

   /* @RequestMapping("updateUpwd")
    @ResponseBody
    public String updateUpwd(){
       // session.getAttribute()
        String uname="classLeader";
        String upwd="1234";
        Md5Hash md5Hash =new Md5Hash(upwd);
        String upwd1 = md5Hash.toString();
        User user =new User();
        user.setUpwd(upwd1);
        user.setUname(uname);
        classLeaderService.updateUpwdByClassLeader(user);
        return "success";
    }*/


    @RequestMapping("selectReport")
    public String selectReport(Model model){
        List<Report> reportList = classLeaderService.selectReport();
        System.out.println(reportList);
        model.addAttribute("reportList",reportList);
        return "reportlist";
    }

    @RequestMapping("selectReportByStuname")
    public String selectReportByStuname(Model model){
        List<Report> reportList = classLeaderService.selectReportByStuname("xin");
        System.out.println(reportList);
        model.addAttribute("reportList",reportList);
        return "reportList";
    }

    @RequestMapping("selectScoreAvg")
    @ResponseBody
    public String selectScoreAvg( ) {
        List<AllColumn> scoreList= classLeaderService.selectScoreAvg("java");
        System.out.println(scoreList);
        return "success";
    }

    @RequestMapping("selectScoreByStuname")
    @ResponseBody
    public String selectScoreByStuname() {
      List<AllColumn> scoreList= classLeaderService.selectScoreByStuname("xin");
      System.out.println(scoreList);
      return "success";
    }



}


