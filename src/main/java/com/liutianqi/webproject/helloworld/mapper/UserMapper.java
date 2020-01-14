package com.liutianqi.webproject.helloworld.mapper;

import com.liutianqi.webproject.helloworld.dto.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert INTO USER (name, code, token) VALUES(#{name}, #{code}, #{token})")
    void insertUser(User user);

    @Select("select * from user where token = #{token}")
    User getUserByToken(@Param("token") String token);
}
