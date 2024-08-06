package com.example.vben_server.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class SystemLoginVo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private String accessToken;


    private String refreshToken;
}
