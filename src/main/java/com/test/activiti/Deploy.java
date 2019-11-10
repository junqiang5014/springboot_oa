package com.test.activiti;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Deploy {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;

    /**
     * 添加一个流程
     * @return
     */
    @RequestMapping("deploy")
    public String deploy(){
        Deployment deploy2 = repositoryService.createDeployment().addClasspathResource("processes/student_holiday.bpmn").deploy();
        Deployment deploy = repositoryService.createDeployment().addClasspathResource("processes/teacher_classLeader_holiday.bpmn").deploy();
        Deployment deploy1 = repositoryService.createDeployment().addClasspathResource("processes/report.bpmn").deploy();
        System.out.println(deploy.getId());
        System.out.println(deploy1.getId());
        System.out.println(deploy2.getId());
        return "success";
    }

    /**
     * 删除一个流程
     * @return
     */
    @RequestMapping("deleteDeployment")
    public String deleteDeployment(){
//        repositoryService.deleteDeployment("25001");
        repositoryService.deleteDeployment("27501");
//        repositoryService.deleteDeployment("25009");
        runtimeService.deleteProcessInstance("report:1:27510","");
        runtimeService.deleteProcessInstance("student_holiday:3:27508","");
        runtimeService.deleteProcessInstance("teacher_classLeader_holiday:1:27509","");
        return "success";
    }

}
