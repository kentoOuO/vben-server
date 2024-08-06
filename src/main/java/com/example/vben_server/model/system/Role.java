package com.example.vben_server.model.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("role") // 指定数据库表名
@ApiModel("角色实体")
public class Role {

    @TableId(type = IdType.AUTO) // 设置为自增类型
    @ApiModelProperty("角色ID")
    private Long id;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("角色值")
    private String roleValue;

    @ApiModelProperty("排序号")
    private String orderNo;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("状态")
    private String status;
}