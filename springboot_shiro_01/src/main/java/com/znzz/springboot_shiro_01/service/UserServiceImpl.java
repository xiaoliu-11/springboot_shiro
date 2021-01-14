package com.znzz.springboot_shiro_01.service;

import com.znzz.springboot_shiro_01.mapper.UserMapper;
import com.znzz.springboot_shiro_01.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User queryUserByName(String name) {
        return userMapper.queryUserByName(name);
    }
}
