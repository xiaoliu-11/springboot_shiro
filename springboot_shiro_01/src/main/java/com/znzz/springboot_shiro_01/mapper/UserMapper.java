package com.znzz.springboot_shiro_01.mapper;


import com.znzz.springboot_shiro_01.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
 public User queryUserByName(String name);
}
