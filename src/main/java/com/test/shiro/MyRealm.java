package com.test.shiro;

import com.test.pojo.Role;
import com.test.pojo.User;
import com.test.service.userService.UsersService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UsersService usersService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String uname = (String) principalCollection.getPrimaryPrincipal();
        User user = usersService.getUserByUname(uname);
        Role role = user.getRole();
        String rolename = role.getRolename();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole(rolename);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String uname = (String) authenticationToken.getPrincipal();
        User user = usersService.getUserByUname(uname);
        String upwd = user.getUpwd();
        System.out.println(upwd);

        return new SimpleAuthenticationInfo(uname,upwd,"myRealm");
    }
}
