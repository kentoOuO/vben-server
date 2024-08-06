package com.example.vben_server.validate.department;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@ApiModel("部门删除参数")
public class DeptDeleteValidate {

    @NotNull(message = "部门ID不能为空")
    @ApiModelProperty(value = "部门ID")
    private Long id;
}