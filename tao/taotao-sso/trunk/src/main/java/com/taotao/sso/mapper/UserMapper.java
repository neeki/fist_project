package com.taotao.sso.mapper;

import org.apache.ibatis.annotations.Param;

import com.taotao.sso.pojo.User;

public interface UserMapper {

    User queryByWhere(@Param("type") Integer type, @Param("param") String param);

    void saveUser(User user);

}
