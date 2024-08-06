package com.example.vben_server.validate.department;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@ApiModel("部门更新参数")
public class DeptUpdateValidate {

    @NotNull(message = "部门ID不能为空")
    @ApiModelProperty(value = "部门ID")
    private Long id;

    @NotEmpty(message = "部门名称不能为空")
    @ApiModelProperty(value = "部门名称")
    private String deptName;

    @ApiModelProperty("上级部门")
    private Long parentDept;

    @NotNull(message = "排序号不能为空")
    @ApiModelProperty("排序号")
    private int orderNo;

    @NotEmpty(message = "状态不能为空")
    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("备注")
    private String remark;
}