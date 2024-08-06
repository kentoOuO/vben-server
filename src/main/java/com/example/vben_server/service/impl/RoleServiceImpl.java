package com.example.vben_server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.vben_server.mapper.System.MenuMapper;
import com.example.vben_server.mapper.System.RoleMapper;
import com.example.vben_server.mapper.System.RoleMenuMapper;
import com.example.vben_server.model.system.Menu;
import com.example.vben_server.model.system.Role;
import com.example.vben_server.model.system.RoleMenu;
import com.example.vben_server.service.RoleService;
import com.example.vben_server.validate.commons.PageValidate;
import com.example.vben_server.validate.role.RoleAfterSearchValidate;
import com.example.vben_server.validate.role.RoleCreateValidate;
import com.example.vben_server.vo.PageResult;
import com.example.vben_server.vo.system.RoleListVo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Resource
    private MenuMapper menuMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Override
    public PageResult<RoleListVo> list(PageValidate pageValidate, RoleAfterSearchValidate searchValidate) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        if (searchValidate.getRoleName() != null && !searchValidate.getRoleName().isEmpty()) {
            queryWrapper.like("role_name", searchValidate.getRoleName());
        }
        if (searchValidate.getStatus() != null && !searchValidate.getStatus().isEmpty()) {
            queryWrapper.like("status", searchValidate.getStatus());
        }

        // 查询符合条件的总数
        Long totalCount = roleMapper.selectCount(queryWrapper);

        // 如果总数小于 (page - 1) * pageSize，返回第一页的数据
        if (totalCount < (pageValidate.getPage() - 1) * pageValidate.getPageSize()) {
            pageValidate.setPage(1); // 设置为第一页
        }

        // 创建分页对象
        Page<Role> page = new Page<>(pageValidate.getPage(), pageValidate.getPageSize());

        // 执行分页查询
        Page<Role> rolePage = roleMapper.selectPage(page, queryWrapper);
        List<RoleListVo> roleList = rolePage.getRecords().stream()
                .map(account -> {
                    RoleListVo roleListVo = new RoleListVo();
                    roleListVo.setId(account.getId());
                    roleListVo.setRoleName(account.getRoleName());
                    roleListVo.setRoleValue(account.getRoleValue());
                    roleListVo.setOrderNo(account.getOrderNo());
                    roleListVo.setCreateTime(account.getCreateTime());
                    roleListVo.setStatus(account.getStatus());
                    roleListVo.setRemark(account.getRemark());

                    return roleListVo;
                }).toList();

        // 构建分页结果
        PageResult<RoleListVo> pageResult = new PageResult<>();
        pageResult.setTotal(rolePage.getTotal());
        pageResult.setItems(roleList);
        return pageResult;
    }

    @Transactional
    public void add(RoleCreateValidate roleCreateValidate) {
        QueryWrapper<Role> queryWrapperName = new QueryWrapper<>();
        queryWrapperName.eq("role_name", roleCreateValidate.getRoleName());
        Long nameCount = roleMapper.selectCount(queryWrapperName);

        if (nameCount > 0) {
            throw new IllegalArgumentException("角色名已存在，请使用其他角色名");
        }

        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_value", roleCreateValidate.getRoleValue());
        Long count = roleMapper.selectCount(queryWrapper);

        if (count > 0) {
            throw new IllegalArgumentException("角色值已存在，请使用其他角色值");
        }

        Role role = new Role();
        role.setOrderNo(roleCreateValidate.getOrderNo());
        role.setRoleName(roleCreateValidate.getRoleName());
        role.setRoleValue(roleCreateValidate.getRoleValue());
        role.setRemark(roleCreateValidate.getRemark());
        role.setStatus(roleCreateValidate.getStatus());
        role.setCreateTime(LocalDateTime.now());

        // 保存角色
        roleMapper.insert(role);

        // 关联菜单
        List<Long> menuIds = roleCreateValidate.getMenuIds();
        for (Long menuId : menuIds) {
            // 根据 menuId 查找菜单
            Menu menu = menuMapper.selectById(menuId);
            if (menu != null) {
                // 插入到 role_menu 关联表
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRoleId(role.getId()); // 使用刚插入的角色 ID
                roleMenu.setMenuId(menuId);
                roleMenuMapper.insert(roleMenu); // 保存关联
            } else {
                throw new IllegalArgumentException("请传入正确菜单id：" + menuId);
            }
        }
    }
}
