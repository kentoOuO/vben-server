package com.example.vben_server.controller.system;

import com.example.vben_server.response.AjaxResult;
import com.example.vben_server.service.RoleService;
import com.example.vben_server.validate.commons.PageValidate;
import com.example.vben_server.validate.role.RoleAfterSearchValidate;
import com.example.vben_server.validate.role.RoleCreateValidate;
import com.example.vben_server.vo.PageResult;
import com.example.vben_server.vo.system.RoleListVo;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/system")
public class RoleController {
    @Resource
    private RoleService roleService;

    @GetMapping("/getRoleList")
    public AjaxResult<PageResult<RoleListVo>> getRoleList(@Validated PageValidate pageValidate, @Validated RoleAfterSearchValidate roleAfterSearchValidate) {
        PageResult<RoleListVo> list = roleService.list(pageValidate,roleAfterSearchValidate);
        return AjaxResult.success(list);
    }

    @PostMapping("/addRole")
    public AjaxResult<?> addRole(@Validated @RequestBody RoleCreateValidate roleCreateValidate){
        roleService.add(roleCreateValidate);
        return AjaxResult.success();
    }
}
