package com.example.vben_server.controller.system;

import com.example.vben_server.response.AjaxResult;
import com.example.vben_server.service.DeptService;
import com.example.vben_server.validate.department.DeptCreateValidate;
import com.example.vben_server.validate.department.DepartmentSearchValidate;
import com.example.vben_server.validate.department.DeptDeleteValidate;
import com.example.vben_server.validate.department.DeptUpdateValidate;
import com.example.vben_server.vo.system.DepartmentListVO;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/system")
public class DeptController {
    @Resource
    private DeptService deptService;

    @GetMapping("/getDeptList")
    @ApiOperation("部门列表")
    public AjaxResult<List<DepartmentListVO>> getDeptList(@Validated DepartmentSearchValidate departmentSearchValidate) {
        List<DepartmentListVO> list = deptService.list(departmentSearchValidate);
        return AjaxResult.success(list);
    }

    @PostMapping("/addDept")
    public AjaxResult<?> addDept(@Validated @RequestBody DeptCreateValidate deptCreateValidate) {
        deptService.add(deptCreateValidate);
        return AjaxResult.success();
    }

    @PostMapping("/updateDept")
    public AjaxResult<?> updateDept(@Validated @RequestBody DeptUpdateValidate deptUpdateValidate) {
        deptService.update(deptUpdateValidate);
        return AjaxResult.success();
    }

    @GetMapping("deleteDept")
    public AjaxResult<?> deleteDept(@Validated DeptDeleteValidate deptDeleteValidate) {
        deptService.delete (deptDeleteValidate);
        return AjaxResult.success();
    }
}
