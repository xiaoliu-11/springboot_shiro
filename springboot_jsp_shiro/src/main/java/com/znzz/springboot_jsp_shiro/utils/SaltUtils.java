package com.znzz.springboot_jsp_shiro.utils;

import java.util.Random;
import java.util.UUID;

public class SaltUtils {


    /**
     *
     * 随机生成salt
     * @param
     */
    public static String getSalt( int n){
         char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabvdefghijklmnopqrstuvwxyz!@#$%^&*012345679".toCharArray();

StringBuilder sb = new StringBuilder();
       for (int i=0; i < n; i++){
            char aChar = chars[new Random().nextInt(chars.length)];
            sb.append(aChar);
       }


        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(getSalt(2));
    }
}
