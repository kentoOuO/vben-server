package com.example.vben_server.validate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("登录参数")
public class SystemLoginValidate implements Serializable {
    @NotEmpty(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名")
    public String username;

    @NotEmpty(message = "密码不能为空")
    @ApiModelProperty(value = "密码")
    private String password;
}
