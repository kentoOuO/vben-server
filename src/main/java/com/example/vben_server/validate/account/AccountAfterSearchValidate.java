package com.example.vben_server.validate.account;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;

@Data
public class AccountAfterSearchValidate implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户名")
    private String account;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("部门")
    private String dept;
}
