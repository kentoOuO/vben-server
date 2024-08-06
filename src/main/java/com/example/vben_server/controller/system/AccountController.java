package com.example.vben_server.controller.system;

import com.example.vben_server.response.AjaxResult;
import com.example.vben_server.service.AccountService;
import com.example.vben_server.validate.account.AccountAfterSearchValidate;
import com.example.vben_server.validate.account.AccountCreateValidate;
import com.example.vben_server.validate.account.AccountUpdateValidate;
import com.example.vben_server.validate.commons.PageValidate;
import com.example.vben_server.vo.PageResult;
import com.example.vben_server.vo.system.AccountListVo;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/system")
public class AccountController {
    @Resource
    private AccountService accountService;

    @GetMapping("/getAccountList")
    @ApiOperation("账号列表")
    public AjaxResult<PageResult<AccountListVo>> getAccountList(@Validated PageValidate pageValidate, @Validated AccountAfterSearchValidate searchValidate) {
        PageResult<AccountListVo> list = accountService.list(pageValidate, searchValidate);
        return AjaxResult.success(list);
    }

    @PostMapping("/addAccount")
    @ApiOperation("新增账号")
    public AjaxResult<?> addAccount(@Validated @RequestBody AccountCreateValidate accountCreateValidate) {
        accountService.add(accountCreateValidate);
        return AjaxResult.success();
    }

    @PostMapping("/updateAccount")
    @ApiOperation("编辑账号")
    public AjaxResult<?> updateAccount(@Validated @RequestBody AccountUpdateValidate accountUpdateValidate) {
        accountService.update(accountUpdateValidate);
        return AjaxResult.success();
    }
}
