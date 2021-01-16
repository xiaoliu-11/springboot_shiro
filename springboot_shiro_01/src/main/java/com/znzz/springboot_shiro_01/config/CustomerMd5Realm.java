package com.znzz.springboot_shiro_01.config;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
/*
 使用自定义realm加入md5+salt+hash

 */

public class CustomerMd5Realm extends AuthorizingRealm {

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        System.out.println("============");
         //拿到用户名，然后去数据库拿权限,角色信息
        String primaryPrincipal = (String) principals.getPrimaryPrincipal();
        System.out.println("身份信息："+ primaryPrincipal);

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        //将数据库中的角色信息赋值给权限对象
        simpleAuthorizationInfo.addRole("admin");


        //将数据库中的权限信息赋值给权限对象
        simpleAuthorizationInfo.addStringPermission("user:*:01");

        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

         //获取身份信息
        String principal = (String) token.getPrincipal();
        if ("lsg".equals(principal)){
            //第三个参数就是，加盐
            return  new SimpleAuthenticationInfo(principal,"e0c13aa03776035c75c96e4b172552c6", ByteSource.Util.bytes("Z*&d"),this.getName());

        }
        return null;
    }
}
