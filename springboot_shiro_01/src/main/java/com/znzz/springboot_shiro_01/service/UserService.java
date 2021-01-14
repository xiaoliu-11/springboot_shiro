package com.znzz.springboot_shiro_01.service;

import com.znzz.springboot_shiro_01.pojo.User;

public interface UserService {
    public User queryUserByName(String name);

}
