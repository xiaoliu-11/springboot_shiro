package com.znzz.springboot_shiro_01.config;


import com.znzz.springboot_shiro_01.pojo.User;
import com.znzz.springboot_shiro_01.service.UserService;
import com.znzz.springboot_shiro_01.service.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


public class UserRealm extends AuthorizingRealm {
   @Autowired
   private UserService userService;


   //授权的地方。
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("执行了授权方法");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
       // info.addStringPermission("user:add");
        //拿到当前登录的对象
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User)subject.getPrincipal();
        info.addStringPermission(currentUser.getPerms());
        System.out.println(currentUser.getPerms());
        return info;
    }




    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了认证方法");

//        String name = "root";
//        String password = "123456";


//
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        //连接真实的数据库
      User user =  userService.queryUserByName(userToken.getUsername());

        if(user == null){
            return null;//抛出异常
        }
        Subject subject1 =  SecurityUtils.getSubject();
        Session session = subject1.getSession();
        session.setAttribute("loginUser",user);


        System.out.println(user.getPassword());
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");

    }
}
