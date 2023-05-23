package com.nuomi.shiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: zh
 * @Date: 2023-05-23-11:37
 * @Description:
 */
@Data
@NoArgsConstructor //无参构造
@AllArgsConstructor //有参构造
public class User {
    private Integer id;
    private String name;
    private String pwd;
    private Integer rid;
}