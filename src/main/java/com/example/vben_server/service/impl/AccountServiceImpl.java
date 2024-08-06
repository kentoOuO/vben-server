package com.example.vben_server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.vben_server.mapper.System.AccountMapper;
import com.example.vben_server.mapper.System.DepartmentMapper;
import com.example.vben_server.model.system.Account;
import com.example.vben_server.model.system.Department;
import com.example.vben_server.service.AccountService;
import com.example.vben_server.validate.account.AccountAfterSearchValidate;
import com.example.vben_server.validate.account.AccountCreateValidate;
import com.example.vben_server.validate.account.AccountUpdateValidate;
import com.example.vben_server.validate.commons.PageValidate;
import com.example.vben_server.vo.PageResult;
import com.example.vben_server.vo.system.AccountListVo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public PageResult<AccountListVo> list(PageValidate pageValidate, AccountAfterSearchValidate searchValidate) {
        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        if (searchValidate.getAccount() != null && !searchValidate.getAccount().isEmpty()) {
            queryWrapper.like("account", searchValidate.getAccount());
        }
        if (searchValidate.getNickname() != null && !searchValidate.getNickname().isEmpty()) {
            queryWrapper.like("nickname", searchValidate.getNickname());
        }
        if (searchValidate.getDept() != null && !searchValidate.getDept().isEmpty()) {
            queryWrapper.eq("dept", searchValidate.getDept());
        }

        // 查询符合条件的总数
        Long totalCount = accountMapper.selectCount(queryWrapper);

        // 如果总数小于 (page - 1) * pageSize，返回第一页的数据
        if (totalCount < (pageValidate.getPage() - 1) * pageValidate.getPageSize()) {
            pageValidate.setPage(1); // 设置为第一页
        }

        // 创建分页对象
        Page<Account> page = new Page<>(pageValidate.getPage(), pageValidate.getPageSize());

        // 执行分页查询
        Page<Account> accountPage = accountMapper.selectPage(page, queryWrapper);
        List<AccountListVo> accountList = accountPage.getRecords().stream()
                .map(account -> {
                    AccountListVo accountListVo = new AccountListVo();
                    accountListVo.setId(account.getId());
                    accountListVo.setAccount(account.getAccount());
                    accountListVo.setNickname(account.getNickname());
                    accountListVo.setEmail(account.getEmail());
                    accountListVo.setRole(account.getRole());
                    accountListVo.setDept(account.getDept());
                    accountListVo.setRemark(account.getRemark());
                    String formattedCreateTime = account.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    accountListVo.setCreateTime(formattedCreateTime);

                    // 获取部门名称
                    String deptName = getDepartmentNameById(account.getDept());
                    accountListVo.setDeptName(deptName);
                    return accountListVo;
                }).collect(Collectors.toList());

        // 构建分页结果
        PageResult<AccountListVo> pageResult = new PageResult<>();
        pageResult.setTotal(accountPage.getTotal());
        pageResult.setItems(accountList);

        return pageResult;
    }


    @Override
    public void add(AccountCreateValidate accountCreateValidate) {
        // 检查是否已存在相同的 account
        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", accountCreateValidate.getAccount());
        Long count = accountMapper.selectCount(queryWrapper);

        if (count > 0) {
            throw new IllegalArgumentException("用户名已存在，请使用其他用户名"); // 抛出异常
        }

        // 创建 Account 实体
        Account account = new Account();
        account.setAccount(accountCreateValidate.getAccount());
        account.setNickname(accountCreateValidate.getNickname());
        account.setEmail(accountCreateValidate.getEmail());
        account.setRole(accountCreateValidate.getRole());
        account.setDept(accountCreateValidate.getDept());
        account.setRemark(accountCreateValidate.getRemark());
        // 设置创建时间
        account.setCreateTime(LocalDateTime.now()); // 使用当前时间
        // 进行插入操作
        accountMapper.insert(account);
    }

    @Override
    public void update(AccountUpdateValidate accountUpdateValidate){
        // 检查是否已存在相同的 account
        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", accountUpdateValidate.getAccount());
        queryWrapper.ne("id", accountUpdateValidate.getId()); // 排除当前正在更新的用户
        long count = accountMapper.selectCount(queryWrapper);

        if (count > 0) {
            throw new IllegalArgumentException("用户名已存在，请使用其他用户名");
        }

        // 更新用户信息
        Account account = new Account();
        account.setId(accountUpdateValidate.getId());
        account.setAccount(accountUpdateValidate.getAccount());
        account.setNickname(accountUpdateValidate.getNickname());
        account.setEmail(accountUpdateValidate.getEmail());
        account.setRole(accountUpdateValidate.getRole());
        account.setDept(accountUpdateValidate.getDept());
        account.setRemark(accountUpdateValidate.getRemark());
        accountMapper.updateById(account);
    }

    private String getDepartmentNameById(String deptId) {
        // 这里可以调用部门服务或者数据库查询部门名称
        Department department = departmentMapper.selectById(deptId);
        return department != null ? department.getDeptName() : ""; // 处理部门未找到的情况
    }
}
