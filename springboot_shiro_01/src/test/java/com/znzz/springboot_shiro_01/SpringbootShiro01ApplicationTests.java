package com.znzz.springboot_shiro_01;

import com.znzz.springboot_shiro_01.service.UserService;
import com.znzz.springboot_shiro_01.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootShiro01ApplicationTests {

    @Autowired
    private UserServiceImpl userService;


    @Test
    public void test1(){
        System.out.println(userService.queryUserByName("tom"));
    }


}
