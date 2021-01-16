package com.znzz.springboot_jsp_shiro.mapper;


import com.znzz.springboot_jsp_shiro.entity.Perms;
import com.znzz.springboot_jsp_shiro.entity.Role;
import com.znzz.springboot_jsp_shiro.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    void save(User user);

    User findByUserName(String username);

    //根据用户名查询角色
    User findRolesByUserName(String username);


    //根据角色的id获取到权限集合
    List<Perms> findPermsByRoleId(String id);


}
