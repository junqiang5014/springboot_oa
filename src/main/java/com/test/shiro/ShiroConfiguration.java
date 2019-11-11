package com.test.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfiguration {

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/loginPage");
        shiroFilterFactoryBean.setSuccessUrl("/indexPage");
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthPage");
        Map<String, String> map = new HashMap<>();
        map.put("loginPage", "anon");
        map.put("login", "anon");
//        map.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    @Bean(name = "securityManager")
    public SecurityManager getSecurityManager(MyRealm myRealm){
        SecurityManager securityManager = new DefaultWebSecurityManager(myRealm);
        return securityManager;
    }

    @Bean(name = "myRealm")
    public MyRealm getMyRealm(CredentialsMatcher credentialsMatcher){
        MyRealm realm = new MyRealm();
        realm.setCredentialsMatcher(credentialsMatcher);
        return realm;
    }

    @Bean("credentialsMatcher")
    public CredentialsMatcher getCredentials(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        return hashedCredentialsMatcher;
    }


    @Bean(name = "shiroDialect")
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
