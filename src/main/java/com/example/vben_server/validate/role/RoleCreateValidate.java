package com.example.vben_server.validate.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("账号创建参数")
public class RoleCreateValidate implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "角色名称不能为空")
    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @NotEmpty(message = "角色值不能为空")
    @ApiModelProperty(value = "角色值")
    private String roleValue;

    @NotEmpty(message = "排序不能为空")
    @ApiModelProperty(value = "排序")
    private String orderNo;

    @NotEmpty(message = "状态不能为空")
    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "菜单权限id列表")
    private List<Long> menuIds;

    private String remark;
}
