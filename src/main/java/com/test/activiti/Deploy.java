package com.test.activiti;

import org.activiti.engine.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Deploy {

    @Autowired
    private RepositoryService repositoryService;

    @RequestMapping("deploy")
    public String deploy1(){
        repositoryService.createDeployment();
        return "index";
    }

    @RequestMapping("deleteDeployment")
    public String deleteDeployment(){
        repositoryService.deleteDeployment("1");
        repositoryService.deleteDeployment("12501");
        repositoryService.deleteDeployment("15001");
        repositoryService.deleteDeployment("17501");
        return "index";

    }

}
