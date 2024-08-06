package com.example.vben_server.model.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("account") // 指定数据库表名
@ApiModel("账号实体")
public class Account {

    @TableId(type = IdType.AUTO) // 设置为自增类型
    @ApiModelProperty("用户ID")
    private Long id;

    @ApiModelProperty("用户名")
    private String account;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("角色")
    private String role;

    @ApiModelProperty("所属部门")
    private String dept;

    @ApiModelProperty("备注")
    private String remark;

}