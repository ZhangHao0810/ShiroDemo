package com.nuomi.shirodemo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

/**
 * @Author: zh
 * @Date: 2023-05-23-9:03
 * @Description: Shiro 进行登录校验，鉴权的测试类
 */
public class ShiroRun {
    public static void main(String[] args) {
        //1. 初始化获取SecurityManager
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();

        //2. 获取Subject对象。利用工具类获取
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        //3. 创建token对象，获取从前端传来的用户名密码。
        AuthenticationToken token = new UsernamePasswordToken("zhangsan","z3");

        //4. 调用对象方法完成登录
        try {
            subject.login(token);
            System.out.println("登录成功");
            // 5. 判断角色
            boolean cto = subject.hasRole("CTO");
            System.out.println("是否拥有CTO角色： "+cto);

            // 6. 判断权限
            boolean permitted = subject.isPermitted("user:insert");
            System.out.println("是否拥有用户插入权限："+permitted);
              // checkPermission方法，没有返回值，检查不通过抛出异常，检查通过放行。
            subject.checkPermission("user:insert");

        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户不存在");
        } catch (IncorrectCredentialsException e){
            e.printStackTrace();
            System.out.println("密码错误");
        } catch (AuthenticationException e){
            e.printStackTrace();
        }
    }
}
