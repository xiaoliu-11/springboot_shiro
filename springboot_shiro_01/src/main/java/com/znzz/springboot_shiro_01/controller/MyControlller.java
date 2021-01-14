package com.znzz.springboot_shiro_01.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyControlller {

    @RequestMapping({"/","/index"})
    public String toIndex(Model model){
       model.addAttribute("msg","hello shiro");
       return "index";

    }

    @RequestMapping("/user/add")
    public String add(){
        return "user/add";

    }
    @RequestMapping("/user/update")
    public String update(){

        return "user/update";

    }
    @RequestMapping("/toLogin")
    public String toLogin(){

        return "login";

    }

    @RequestMapping("/login")
    public String Login(String username ,String password,Model model){
        //获取当前的用户
        Subject subject = SecurityUtils.getSubject();

        //封装用户的数据
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);
        try{
            subject.login(usernamePasswordToken);//执行登录的方法
            return "index";
        }catch (UnknownAccountException e){ // 用户名不存在
             model.addAttribute("msg","用户名错误！");
             return "login";
        }catch (IncorrectCredentialsException e){ // 密码错误
            model.addAttribute("msg","密码错误！");
            return "login";
        }

    }

    @RequestMapping("noauth")
    @ResponseBody
    public String unauthorized(){
        return "没有授权无法访问此页面！";
    }

}
