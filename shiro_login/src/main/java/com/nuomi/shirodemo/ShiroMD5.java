package com.nuomi.shirodemo;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @Author: zh
 * @Date: 2023-05-23-9:49
 * @Description: Shiro 加密相关的测试类
 */
public class ShiroMD5 {

    public static void main(String[] args) {
        // 密码明文:
        String password = "z3";
        // 使用MD5 加密
        Md5Hash md5Hash = new Md5Hash(password);
        System.out.println("md5加密后的密码: " + md5Hash);
        // 加盐
        Md5Hash md5HashSalt = new Md5Hash(password,"起司社区");
        System.out.println("md5HashSalt = " + md5HashSalt);
        // 为了最大程度保证密码安全，可以多次加密 (带盐的md5多次加密）
        Md5Hash md5HashMuti = new Md5Hash(password,"梦中的迷醉乡",3);
        System.out.println("md5HashMuti = " + md5HashMuti);

        // 使用父类进行加密,通过父类指定加密类型并且带盐多次
        SimpleHash simpleHash = new SimpleHash("MD5",password,"salt",3);
        System.out.println("simpleHash = " + simpleHash);

    }
}
