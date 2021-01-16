package com.znzz.springboot_jsp_shiro.shiro;


import com.znzz.springboot_jsp_shiro.entity.Perms;
import com.znzz.springboot_jsp_shiro.entity.Role;
import com.znzz.springboot_jsp_shiro.entity.User;
import com.znzz.springboot_jsp_shiro.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


//自定义的realm的类
//用来进行数据校验
public class CustomerRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取身份信息
        String  primaryPrincipal =(String) principals.getPrimaryPrincipal();
        System.out.println("调用授权验证"+primaryPrincipal);
         //根据身份信息获取角色信息
        User user  =   userService.findRolesByUserName(primaryPrincipal);
        System.out.println(userService.findRolesByUserName(primaryPrincipal));
       //角色不为空，构建角色信息
        //授权角色信息
        if(!CollectionUtils.isEmpty(user.getRoles())){
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            user.getRoles().forEach(role -> {
                simpleAuthorizationInfo.addRole(role.getName());
                System.out.println("=========");
                System.out.println("用户的角色："+role.getName());


                //角色遍历出来了，现在还需要权限。
                List<Perms> perms = userService.findPermsByRoleId(role.getId());
                if(!CollectionUtils.isEmpty(perms)){
                    perms.forEach(perm -> {
                        //添加权限信息
                        simpleAuthorizationInfo.addStringPermission(perm.getName());
                        System.out.println("=========");
                        System.out.println("用户的权限："+perm.getName());
                    });

                }


            });
         return simpleAuthorizationInfo;
        }


        return null;
    }


    //用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("=======================");

        String principal = (String) token.getPrincipal();

        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
       //获取身份信息
        User userName = userService.findByUserName(userToken.getUsername());
        System.out.println("密码：==========="+ userName.getPassword());
        System.out.println("用户名：=============="+userName.getUsername());

        if (userName != null){
            return new SimpleAuthenticationInfo(userName.getUsername(),userName.getPassword(), ByteSource.Util.bytes(userName.getSalt()),this.getName());
        }

        return null;

    }
}
