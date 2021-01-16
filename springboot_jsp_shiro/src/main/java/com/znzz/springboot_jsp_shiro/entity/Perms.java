package com.znzz.springboot_jsp_shiro.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Perms {
    private String id;
    private String name;
    private String url;

}
