package com.test.controller.classLeaderController;


import com.test.pojo.ClassLeader;
import com.test.pojo.Report;
import com.test.pojo.Score;
import com.test.pojo.User;
import com.test.service.classLeaderService.ClassLeaderService;
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

    @RequestMapping("selectMessage")
    public String selectMessage(Model model){
         ClassLeader classLeader= classLeaderService.selectMessage(1);
        model.addAttribute("classLeader",classLeader);
        return "viewstu";
    }


    @RequestMapping("selectReport")
    @ResponseBody
    public String selectReport( ){
        List<Report> reportList = classLeaderService.selectReport();
        System.out.println(reportList);
       // model.addAttribute("reportList",reportList);
        return "success";
    }

    @RequestMapping("selectReportByStuname")
    @ResponseBody
    public String selectReportByStuname( ){
        List<Report> reportList = classLeaderService.selectReportByStuname("xin");
        System.out.println(reportList);
        // model.addAttribute("reportList",reportList);
        return "success";
    }

    @RequestMapping("selectScoreAvg")
    @ResponseBody
    public String selectScoreAvg( ) {
        List<Score> scoreList= classLeaderService.selectScoreAvg("java");
        System.out.println(scoreList);
        return "success";
    }

}


