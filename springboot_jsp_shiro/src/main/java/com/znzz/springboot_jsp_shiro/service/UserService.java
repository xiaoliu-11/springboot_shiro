package com.znzz.springboot_jsp_shiro.service;

import com.znzz.springboot_jsp_shiro.entity.Perms;
import com.znzz.springboot_jsp_shiro.entity.Role;
import com.znzz.springboot_jsp_shiro.entity.User;

import java.util.List;

public interface UserService {
    void register(User user);

    User findByUserName(String username);

    //根据用户名查询角色

    User findRolesByUserName(String username);

    //根据角色的id获取到权限信息
  List<Perms> findPermsByRoleId(String id);



}
