
package com.test.controller.classLeaderController;


import com.test.pojo.*;
import com.test.service.classLeaderService.ClassLeaderService;
import com.test.util.ExcleUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
    @RequestMapping("echarts_class")
    public String selectScoreAvg(Model model,String classname) {
        List<Double> avgList =new ArrayList<Double>();
        List<AllColumn> scoreList= classLeaderService.selectScoreAvg(classname);
        for (AllColumn avg : scoreList){
            avgList.add(avg.getAVG());
        }
        model.addAttribute("avgList",avgList);
        return "classLeader/echarts_class";
    }

    //查看学生成绩走势
    @RequestMapping("echarts")
    public String echarts(Model model,String stuname){
        List<Integer> studentList =new ArrayList<Integer>();
        List<AllColumn> allColumnList=classLeaderService.selectScoreByStuname(stuname);
        System.out.println(allColumnList);
        for (AllColumn a :allColumnList){
            studentList.add(a.getScore());
        }
        model.addAttribute("studentList",studentList);
        return "classLeader/echarts";
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

    @RequestMapping("import")
    public String impot(){
        return "classLeader/import";
    }

    @RequestMapping(value = "savStu", method = RequestMethod.POST)
    @ResponseBody
    public String savStu(MultipartFile file, HttpServletRequest request){
        String contentType = file.getContentType();

        String fileName = file.getOriginalFilename();

        if (file.isEmpty()) {
            return "文件为空！";
        }
        try {
            //根据路径获取这个操作excel的实例
            HSSFWorkbook wb = new HSSFWorkbook(file.getInputStream());            //根据页面index 获取sheet页

            HSSFSheet sheet = wb.getSheetAt(0);
            //实体类集合
            List<Student> importDatas = new ArrayList<Student>();
            HSSFRow row = null;

            //循环sesheet页中数据从第二行开始，第一行是标题
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                //获取每一行数据
                row = sheet.getRow(i);
                Student stu = new Student();
                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
////
                row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);

                stu.setStuid(Integer.parseInt(row.getCell(0).getStringCellValue()));
                  //stu.setStuid(Integer.valueOf((int) row.getCell(0).getNumericCellValue()));
                stu.setStuname(row.getCell(1).getStringCellValue());
                stu.setStuage(Integer.parseInt(row.getCell(2).getStringCellValue()));
                stu.setStusex(row.getCell(3).getStringCellValue());
              //  stu.setScore(Integer.valueOf((int) row.getCell(4).getNumericCellValue()));

                //SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                //data.setCreateDate(df.parse(df.format(HSSFDateUtil.getJavaDate(row.getCell(2).getNumericCellValue()))));

                //data.setAge(Integer.valueOf((int) row.getCell(3).getNumericCellValue()));
                importDatas.add(stu);
            }
            //循环展示导入的数据，实际应用中应该校验并存入数据库

            for (Student imdata : importDatas) {
                classLeaderService.insertStudentMessage(imdata);
                System.out.println("ID:"+imdata.getStuid()+" name:"+imdata.getStuname());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "importSucPage";
    }




}


