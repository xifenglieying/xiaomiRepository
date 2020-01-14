package com.liutianqi.webproject.helloworld.mapper;


import com.liutianqi.webproject.helloworld.dto.Publish;
import org.apache.ibatis.annotations.Insert;

public interface PublishMapper {

    @Insert("insert into Publish values (#{question}, #{tag}, #{title})")
    void insertPublish(Publish publish);
}
