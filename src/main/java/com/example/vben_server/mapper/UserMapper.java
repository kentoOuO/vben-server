package com.example.vben_server.mapper;

import com.example.vben_server.model.User;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends MPJBaseMapper<User> {
}