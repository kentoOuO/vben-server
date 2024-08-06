package com.example.vben_server.vo.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ApiModel("账号列表Vo")
public class RoleListVo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "角色值")
    private String roleValue;

    @ApiModelProperty(value = "排序")
    private String orderNo;

    @ApiModelProperty(value = "角色菜单权限")
    private String menu;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "备注")
    private String remark;
}
