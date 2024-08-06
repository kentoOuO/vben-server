package com.example.vben_server.model.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("department") // 指定数据库表名
@ApiModel("部门实体")
public class Department {

    @TableId(type = IdType.AUTO) // 设置为自增类型
    @ApiModelProperty("部门ID")
    private Long id;

    @ApiModelProperty("部门名称")
    private String deptName;

    @ApiModelProperty("排序号")
    private int orderNo;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("上级部门ID")
    private Long parentDeptId; // 你可以根据需求调整类型
}