package com.znzz.springboot_jsp_shiro.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {

    @RequestMapping("/save")
    public String save(){
        //获得主体对象
        Subject subject = SecurityUtils.getSubject();
        //通过代码的方式来判断是否有权限

        if(subject.hasRole("admin")){
            System.out.println("保存订单");
        }else {
            System.out.println("你没有权限");
        }


        return "redirect:/index.jsp";
    }

}
