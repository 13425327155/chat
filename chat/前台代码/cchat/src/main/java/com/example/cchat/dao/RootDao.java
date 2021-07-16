package com.example.cchat.dao;

import com.example.cchat.entity.Root;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface RootDao {
    Root findByName(@Param("user_name") String user_name);

}
