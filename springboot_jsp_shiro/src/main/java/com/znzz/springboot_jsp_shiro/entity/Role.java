package com.znzz.springboot_jsp_shiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private String id;
    private String name;


    //定义一个权限的集合，因为权限时跟角色绑定的

    private List<Perms> perms;
}
