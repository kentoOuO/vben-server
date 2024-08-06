package com.example.vben_server.service;

import com.example.vben_server.validate.SystemLoginValidate;
import org.springframework.stereotype.Service;
import com.example.vben_server.vo.SystemLoginVo;

@Service
public interface SystemLoginService {
    /**
     * 登录
     *
     * @author fzr
     * @param systemLoginValidate 登录参数
     * @return SystemLoginVo
     */
    SystemLoginVo login(SystemLoginValidate systemLoginValidate);
}
