
package com.test.controller.classLeaderController;


import com.test.pojo.*;
import com.test.service.classLeaderService.ClassLeaderService;
import com.test.util.ExcleUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ClassLeaderController {
    @Autowired
    private ClassLeaderService classLeaderService;
    @Autowired
    private SecurityManager securityManager;


    // 查看班主任信息
    @RequestMapping("selectMessage")

    public String selectMessage(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");

        ClassLeader classLeader= classLeaderService.selectMessage(user.getUid());

        model.addAttribute("classLeader",classLeader);
        return "classLeader/index";
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

    @RequestMapping("echarts")
    public String echarts(Model model,String stuname){
        List<AllColumn> allColumnList=classLeaderService.selectScoreByStuname("stuname");
        model.addAttribute("allColumnList",allColumnList);
        return "echarts";
    }


        //查看周报
    @RequestMapping("selectReport")
    public String selectReport(Model model){
        List<Report> reportList = classLeaderService.selectReport();
        System.out.println(reportList);
        model.addAttribute("reportList",reportList);
        return "classLeader/reportList";
    }

    //按姓名筛选周报
    @RequestMapping("selectReportByStuname")
    public String selectReportByStuname(Model model){
        List<Report> reportList = classLeaderService.selectReportByStuname("xin");
        System.out.println(reportList);
        model.addAttribute("reportList",reportList);
        return "reportList";
    }
    //查看班级平均成绩
    @RequestMapping("selectScoreAvg")
    @ResponseBody
    public String selectScoreAvg( ) {
        List<AllColumn> scoreList= classLeaderService.selectScoreAvg("java");
        System.out.println(scoreList);
        return "success";
    }

    //查看学生成绩走势
    @RequestMapping("selectScoreByStuname")
    @ResponseBody
    public String selectScoreByStuname() {
      List<AllColumn> scoreList= classLeaderService.selectScoreByStuname("xin");
      System.out.println(scoreList);
      return "success";
    }
    //查看学生信息
    @RequestMapping("selectStudent")
    public String selectStudent(Model model) {
        List<Student> studentList= classLeaderService.selectStudent();
        model.addAttribute("studentList",studentList);
        return "test_xin";
    }


    /**
     * 导出报表
     *
     * @return
     */
    @RequestMapping("export")
    @ResponseBody
    public void export(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取数据
        List<Student> list =classLeaderService.export();
        //excel标题
        String[] title = {"学生ID", "学生姓名", "学生年龄", "学生性别","班级"};

        //excel文件名
        String fileName = "学生信息表" + System.currentTimeMillis() + ".xls";

        //sheet名
        String sheetName = "学生信息表";

        String [][] content = new String[list.size()][5];

        for (int i = 0; i < list.size(); i++) {
            content[i] = new String[title.length];
            Student obj = list.get(i);
            content[i][0] = String.valueOf(obj.getStuid());
            content[i][1] = obj.getStuname();
            content[i][2] = String.valueOf(obj.getStuage());
            content[i][3] = obj.getStusex();
           content[i][4] = String.valueOf(obj.getClasses().getCid());
        }

        //创建HSSFWorkbook
        HSSFWorkbook wb = ExcleUtil.getHSSFWorkbook(sheetName, title, content, null);

        //响应到客户端
        try {
            this.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送响应流方法
     */
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}


