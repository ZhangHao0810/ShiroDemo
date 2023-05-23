package com.nuomi.shirodemo;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

/**
 * @Author: zh
 * @Date: 2023-05-23-10:08
 * @Description: 登录认证信息的改造
 */
public class MyRealm extends AuthenticatingRealm {

    /**
     * @description 自定义的登录认证方法。Shiro的 login方法底层会调用该类的认证方法进行认证。
     * 需要通过配置 告诉Shiro 让自定义的Realm生效。
     * 1. ini文件中配置
     * 2. SpringBoot框架配置
     * 该方法只是获取进行对比的信息，认证逻辑还是按照Shiro底层认证逻辑完成。
     * @author zh
     * @data 2023/5/23
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 1. 获取身份信息
        String principal = authenticationToken.getPrincipal().toString();
        // 2. 获取凭证信息
        String password = new String((char[]) authenticationToken.getCredentials());
        System.out.println("登录用户信息：" + principal + "----- 密码：" + password);
        // 3. 访问数据库，获取库中存储的用户信息
        if ("zhangsan".equals(principal)) {
            // 3.1 从数据库中查出加密后的密码
            String pwdInfo = "7174f64b13022acd3c56e2781e098a5f";

            // 4. 创建封装校验逻辑的对象，封装数据返回
            AuthenticationInfo info = new SimpleAuthenticationInfo(
                    authenticationToken.getPrincipal(),
                    pwdInfo,
                    ByteSource.Util.bytes("salt"),
                    authenticationToken.getPrincipal().toString()
            );
            return info;
        }


        return null;
    }
}
