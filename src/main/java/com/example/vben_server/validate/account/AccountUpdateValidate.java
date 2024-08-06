package com.example.vben_server.validate.account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@ApiModel("账号更新参数")
public class AccountUpdateValidate {

    @NotNull(message = "账号ID不能为空")
    @ApiModelProperty(value = "账号ID")
    private Long id;

    @NotEmpty(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名")
    private String account;

    @NotEmpty(message = "昵称不能为空")
    @ApiModelProperty(value = "昵称")
    private String nickname;

    @NotEmpty(message = "邮箱不能为空")
    @ApiModelProperty(value = "邮箱")
    private String email;

    @NotEmpty(message = "角色不能为空")
    @ApiModelProperty(value = "角色")
    private String role;

    @NotEmpty(message = "部门不能为空")
    @ApiModelProperty(value = "部门")
    private String dept;

    private String remark;
}
