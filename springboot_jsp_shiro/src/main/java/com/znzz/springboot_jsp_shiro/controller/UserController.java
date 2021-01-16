package com.znzz.springboot_jsp_shiro.controller;

import com.znzz.springboot_jsp_shiro.entity.User;
import com.znzz.springboot_jsp_shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

      @Autowired
      private UserService userService;

    /**
     * 用来处理身份认证
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
     public String login(String username, String password,Model model){

        //获取主体对象
       Subject subject = SecurityUtils.getSubject();
       try {
           subject.login(new UsernamePasswordToken(username,password));
            return "redirect:/index.jsp";

       }catch (UnknownAccountException e){
            e.printStackTrace();
           System.out.println("用户名错误");
       }catch (IncorrectCredentialsException e){
           e.printStackTrace();
           System.out.println("密码错误");
       } catch (Exception e){
           e.printStackTrace();
           System.out.println("请输入完整信息");

       }
        return "redirect:/login.jsp";

    }


    /**
     * 退出登录
     *
     */
@RequestMapping("logout")
    public String logout(){
    Subject subject = SecurityUtils.getSubject();
    subject.logout();
    return "redirect:/login.jsp";
}


/**
 * 用户注册校验
 */

@RequestMapping("/register")
public String register(User user){
try {
    userService.register(user);
    return "redirect:/login.jsp";
}catch (Exception e){
    e.printStackTrace();
    return "redirect:/register.jsp";
}

}



}
