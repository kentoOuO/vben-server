package com.example.vben_server.validate.commons;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@ApiModel("列表分页参数")
public class PageValidate implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    // 当前分页
    @ApiModelProperty(value = "当前分页")
    @DecimalMin(value = "1", message = "page参数必须大于0的数字")
    public long page = 1;

    // 每页条数
    @ApiModelProperty(value = "分页数量")
    @DecimalMin(value = "1", message = "pageSize参数必须是大于0的数字")
    @DecimalMax(value = "60", message = "pageSize参数必须是小于60的数字")
    private long pageSize = 10;
}
