package com.znzz.springboot_jsp_shiro.service;

import com.znzz.springboot_jsp_shiro.entity.Perms;
import com.znzz.springboot_jsp_shiro.entity.Role;
import com.znzz.springboot_jsp_shiro.entity.User;
import com.znzz.springboot_jsp_shiro.mapper.UserMapper;
import com.znzz.springboot_jsp_shiro.utils.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{
   @Autowired
   private UserMapper userMapper;

    @Override
    public void register(User user) {
        //处理业务
        //铭文的密码进行md5+ salt+ hash加密。
        //1.生成随机salt
        String salt = SaltUtils.getSalt(8)  ;
        user.setSalt(salt);
        Md5Hash md5Hash = new Md5Hash(user.getPassword(),salt,1024);
        user.setPassword(md5Hash.toHex());
        userMapper.save(user);

    }

    @Override
    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }


    //根据用户名查询角色
    @Override
    public User findRolesByUserName(String username) {


        return userMapper.findRolesByUserName(username);
    }


    //根据角色的id获取权限
    @Override
    public List<Perms> findPermsByRoleId(String id) {


        return userMapper.findPermsByRoleId(id);
    }
}
