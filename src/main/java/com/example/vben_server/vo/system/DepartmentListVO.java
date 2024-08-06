package com.example.vben_server.vo.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel("部门列表Vo")
public class DepartmentListVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private long id;

    @ApiModelProperty(value = "部门名称")
    private String deptName;

    @ApiModelProperty(value = "排序号")
    private int orderNo;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "上级部门ID")
    private Long parentDept;

    @ApiModelProperty(value = "子部门列表")
    private List<DepartmentListVO> children = new ArrayList<>(); // 初始化子节点列表
}