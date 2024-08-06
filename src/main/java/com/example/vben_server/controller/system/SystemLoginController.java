package com.example.vben_server.controller.system;

import cn.dev33.satoken.stp.StpUtil;
import com.example.vben_server.annotation.NotLogin;
import com.example.vben_server.response.AjaxResult;
import com.example.vben_server.service.SystemLoginService;
import com.example.vben_server.validate.SystemLoginValidate;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.vben_server.vo.SystemLoginVo;

@RestController
@RequestMapping("/api")
public class SystemLoginController {

    @Resource
    private SystemLoginService systemLoginService;

    @NotLogin
    @PostMapping("/login")
    public AjaxResult<SystemLoginVo> login(@Validated @RequestBody SystemLoginValidate systemLoginValidate) {
        SystemLoginVo systemLoginVo = systemLoginService.login(systemLoginValidate);
        return AjaxResult.success(systemLoginVo);
    }

    @GetMapping("/getUserInfo")
    public AjaxResult<?> getUser() {
        return AjaxResult.success("User Info");
    }

    @GetMapping("/codes")
    public AjaxResult<?> getCodes() {
        return AjaxResult.success("codes");
    }

    @GetMapping("/logout")
    public AjaxResult<?> logout() {
        StpUtil.logout();
        return AjaxResult.success("Logout Successful");
    }
}
