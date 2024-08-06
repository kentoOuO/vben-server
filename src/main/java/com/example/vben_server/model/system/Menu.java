package com.example.vben_server.model.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("menu")
@ApiModel("菜单实体")
public class Menu {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("菜单ID")
    private Long id;

    @ApiModelProperty("菜单值")
    private String menuValue;
}
