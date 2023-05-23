package com.nuomi.shiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nuomi.shiro.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

import static java.io.File.separator;

/**
 * @Author: zh
 * @Date: 2023-05-23-11:39
 * @Description:
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT NAME FROM role WHERE id IN (SELECT rid FROM role_user WHERE uid=(SELECT id FROM USER WHERE NAME=#{principal}))")
    List<String> getUserRoleInfoMapper(@Param("principal") String principal);

    @Select({
            "<script>",
            "select info FROM permissions WHERE id IN ",
            "(SELECT pid FROM role_ps WHERE rid IN (",
            "SELECT id FROM role WHERE NAME IN ",
            "<foreach collection='roles' item='name' open='(' separator=',' close=')'>",
            "#{name}",
            "</foreach>",
            "))",
            "</script>"
    })
    List<String> getUserPermissionInfoMapper(@Param("roles") List<String> roles);


}