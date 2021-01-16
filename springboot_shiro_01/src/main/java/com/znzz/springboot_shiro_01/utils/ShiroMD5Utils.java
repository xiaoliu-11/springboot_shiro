package com.znzz.springboot_shiro_01.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

public class ShiroMD5Utils {

    public static void main(String[] args) {

        //使用md5
        Md5Hash md5Hash = new Md5Hash("123");
        System.out.println(md5Hash.toHex());
        //使用md5 + salt
        Md5Hash md5Hash1 = new Md5Hash("123","Z*&d");
        System.out.println(md5Hash1.toHex());
        //使用md5 + salt + hash
        Md5Hash md5Hash2 = new Md5Hash("123","Z*&d",1024);
        System.out.println(md5Hash2.toHex());


    }

}
