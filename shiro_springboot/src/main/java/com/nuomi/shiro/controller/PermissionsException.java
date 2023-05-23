package com.nuomi.shiro.controller;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: zh
 * @Date: 2023-05-23-15:16
 * @Description: 创建认证异常处理类，使用@ControllerAdvice 加@ExceptionHandler 实现特殊异常处理。 异常不会再抛出，更加专业化。
 */
@ControllerAdvice
public class PermissionsException {
    @ResponseBody
    @ExceptionHandler(UnauthorizedException.class)
    public String unauthorizedException(Exception ex){
        return "无权限";
    }
    @ResponseBody
    @ExceptionHandler(AuthorizationException.class)
    public String authorizationException(Exception ex){
        return "权限认证失败";
    }
}

