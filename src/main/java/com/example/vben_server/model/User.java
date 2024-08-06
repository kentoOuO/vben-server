package com.example.vben_server.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("users") // 对应数据库表名
public class User {

    @ApiModelProperty("Id")
    private Long id;

    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    private String password;
}