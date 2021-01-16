package com.znzz.springboot_jsp_shiro.config;


import com.znzz.springboot_jsp_shiro.shiro.CustomerRealm;
import com.znzz.springboot_jsp_shiro.shiro.RedisCacheManager;
import lombok.val;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.management.MXBean;
import java.util.HashMap;
import java.util.Map;

/**
 * 用来整合shiro的配置类
 */
@Configuration
public class ShiroConfig {
      //1.创建shiroFilter
      //负责拦截所有请求

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
     ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
     //给filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
   //配置系统的受限资源
        Map<String, String> map = new HashMap<String, String>();
        map.put("/index.jsp","authc");//authc表示访问这个资源需要认证和授权
        map.put("/user/register","anon");
        map.put("/register.jsp","anon");//anon表示公共资源，都可以访问的到。
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        //配置系统的公共资源

        //默认认证的界面路径，（及你访问没有权限的页面时，会跳转到该页面。）就算你不配置，也会跳转到这个login页面。
        shiroFilterFactoryBean.setLoginUrl("/login.jsp");




      return shiroFilterFactoryBean;
    }
      //2.创建安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(Realm realm){
         DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
         //给安全管理器设置realm

        defaultWebSecurityManager.setRealm(realm);
         return defaultWebSecurityManager;
    }


      //3.创建自定义realm
    @Bean
     public Realm getRealm(){
         CustomerRealm customerRealm = new CustomerRealm();
         //修改凭证校验匹配器
         HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
          //设置加密算法为md5
        credentialsMatcher.setHashAlgorithmName("MD5");
        //设置散列次数
        credentialsMatcher.setHashIterations(1024);
       customerRealm.setCredentialsMatcher(credentialsMatcher);




       //开启缓存管理


       //shiro默认的缓存管理器,你也可以存入redis中。该项目没有
      customerRealm.setCacheManager(new EhCacheManager());


      customerRealm.setCachingEnabled(true);// 开启全局缓存
      customerRealm.setAuthenticationCachingEnabled(true);//开启认证缓存
      customerRealm.setAuthenticationCacheName("authentication");
      customerRealm.setAuthorizationCachingEnabled(true);
      customerRealm.setAuthorizationCacheName("authorization");//开启授权的缓存
        return customerRealm;
}

}
