package com.example.vben_server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.vben_server.mapper.System.AccountMapper;
import com.example.vben_server.mapper.System.DepartmentMapper;
import com.example.vben_server.model.system.Account;
import com.example.vben_server.model.system.Department;
import com.example.vben_server.service.DeptService;
import com.example.vben_server.validate.department.DeptCreateValidate;
import com.example.vben_server.validate.department.DepartmentSearchValidate;
import com.example.vben_server.validate.department.DeptDeleteValidate;
import com.example.vben_server.validate.department.DeptUpdateValidate;
import com.example.vben_server.vo.system.DepartmentListVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class DeptServiceImpl implements DeptService {
    @Resource
    private DepartmentMapper departmentMapper;
    @Resource
    private AccountMapper accountMapper; ;

    @Override
    public List<DepartmentListVO> list(DepartmentSearchValidate departmentSearchValidate) {
        QueryWrapper<Department> queryWrapper = new QueryWrapper<>();
        if (departmentSearchValidate.getDeptName() != null && !departmentSearchValidate.getDeptName().isEmpty()) {
            queryWrapper.like("dept_name", departmentSearchValidate.getDeptName());
        }
        if (departmentSearchValidate.getStatus() != null && !departmentSearchValidate.getStatus().isEmpty()) {
            queryWrapper.like("status", departmentSearchValidate.getStatus());
        }

        // 查询所有数据
        List<Department> departmentList = departmentMapper.selectList(queryWrapper);

        // 创建一个用于存储所有部门的 Map
        Map<Long, DepartmentListVO> departmentMap = new HashMap<>();
        for (Department department : departmentList) {
            DepartmentListVO vo = new DepartmentListVO();
            vo.setId(department.getId());
            vo.setDeptName(department.getDeptName());
            vo.setOrderNo(department.getOrderNo());
            vo.setCreateTime(department.getCreateTime());
            vo.setRemark(department.getRemark());
            vo.setStatus(department.getStatus());
            vo.setParentDept(department.getParentDeptId());
            vo.setChildren(new ArrayList<>()); // 初始化子节点列表
            departmentMap.put(department.getId(), vo);
        }

        // 构建树结构
        List<DepartmentListVO> treeList = new ArrayList<>();
        for (DepartmentListVO vo : departmentMap.values()) {
            if (vo.getParentDept() == null) {
                // 如果没有父部门，说明是根节点
                treeList.add(vo);
            } else {
                // 找到父节点并添加到子节点列表中
                DepartmentListVO parent = departmentMap.get(vo.getParentDept());
                if (parent != null) {
                    parent.getChildren().add(vo);
                }
            }
        }

        // 对每一层的子节点进行排序
        sortChildren(treeList);

        return treeList;
    }

    @Override
    public void add(DeptCreateValidate deptCreateValidate) {
        QueryWrapper<Department> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dept_name", deptCreateValidate.getDeptName());
        Long count = departmentMapper.selectCount(queryWrapper);

        if (count > 0) {
            throw new IllegalArgumentException("部门名称已存在,请更换"); // 抛出异常
        }

        Department department = new Department();
        department.setDeptName(deptCreateValidate.getDeptName());
        department.setOrderNo(deptCreateValidate.getOrderNo());
        department.setRemark(deptCreateValidate.getRemark());
        department.setStatus(deptCreateValidate.getStatus());

        // 设置父级部门ID，如果 parentDept 为空则为第一级部门
        Long parentDeptId = deptCreateValidate.getParentDept();
        department.setParentDeptId(parentDeptId);

        // 设置创建时间
        department.setCreateTime(LocalDateTime.now());

        // 保存部门信息
        departmentMapper.insert(department);
    }

    @Override
    public void update(DeptUpdateValidate deptUpdateValidate) {
        QueryWrapper<Department> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dept_name", deptUpdateValidate.getDeptName());
        queryWrapper.ne("id", deptUpdateValidate.getId()); // 排除当前正在更新的用户
        Long count = departmentMapper.selectCount(queryWrapper);

        if (count > 0) {
            throw new IllegalArgumentException("部门名称已存在,请更换"); // 抛出异常
        }

        Department department = new Department();
        department.setId(deptUpdateValidate.getId());
        department.setDeptName(deptUpdateValidate.getDeptName());
        department.setOrderNo(deptUpdateValidate.getOrderNo());
        department.setRemark(deptUpdateValidate.getRemark());
        department.setStatus(deptUpdateValidate.getStatus());
        departmentMapper.updateById(department);
    }

    @Override
    public void delete(DeptDeleteValidate deptDeleteValidate) {
        QueryWrapper<Department> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_dept_id", deptDeleteValidate.getId());
        Long count = departmentMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new IllegalArgumentException("存在子部门，无法删除!");
        }

        QueryWrapper<Account> accountQueryWrapper = new QueryWrapper<>();
        accountQueryWrapper.eq("dept", deptDeleteValidate.getId());
        Long accountCount = accountMapper.selectCount(accountQueryWrapper);
        if (accountCount > 0) {
            throw new IllegalArgumentException("部门内存在关联账号，无法删除!");
        }

        departmentMapper.deleteById(deptDeleteValidate.getId());

    }


    private void sortChildren(List<DepartmentListVO> treeList) {
        // 首先对第一层节点进行排序
        treeList.sort(Comparator.comparing(DepartmentListVO::getOrderNo));

        // 然后递归排序每个节点的子节点
        for (DepartmentListVO node : treeList) {
            if (node.getChildren() != null && !node.getChildren().isEmpty()) {
                // 排序当前节点的子节点
                node.getChildren().sort(Comparator.comparing(DepartmentListVO::getOrderNo));
                // 递归排序子节点的子节点
                sortChildren(node.getChildren());
            }
        }
    }
}
