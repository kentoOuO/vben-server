package com.example.vben_server.validate.role;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RoleAfterSearchValidate{

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("状态")
    private String status;
}
