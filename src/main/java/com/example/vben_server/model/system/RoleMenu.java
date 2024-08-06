package com.example.vben_server.model.system;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("role_menu")
@ApiModel("角色菜单关系表")
public class RoleMenu {
    @TableId
    @ApiModelProperty("角色id")
    private Long roleId;
    @ApiModelProperty("菜单id")
    private Long menuId;
}
