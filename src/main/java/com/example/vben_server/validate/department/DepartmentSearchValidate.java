package com.example.vben_server.validate.department;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class DepartmentSearchValidate implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("部门名称")
    private String deptName;

    @ApiModelProperty("状态")
    private String status;
}
