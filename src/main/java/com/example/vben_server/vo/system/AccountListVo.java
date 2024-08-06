package com.example.vben_server.vo.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@ApiModel("账号列表Vo")
public class AccountListVo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String account;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "角色")
    private String role;

    @ApiModelProperty(value = "所属部门")
    private String dept;

    @ApiModelProperty(value = "所属部门名称")
    private String deptName;

    @ApiModelProperty(value = "备注")
    private String remark;

}
