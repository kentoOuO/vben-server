package com.example.vben_server.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.vben_server.model.User;
import com.example.vben_server.mapper.UserMapper;
import com.example.vben_server.service.SystemLoginService;
import com.example.vben_server.validate.SystemLoginValidate;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import com.example.vben_server.vo.SystemLoginVo;

@Service
public class SystemLoginServiceImpl implements SystemLoginService {

    @Resource
    private UserMapper userMapper;

    @Override
    public SystemLoginVo login(SystemLoginValidate systemLoginValidate) {
        // 从数据库中查找用户
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", systemLoginValidate.getUsername()));

        // 验证用户是否存在以及密码是否匹配
        if (user != null && user.getPassword().equals(systemLoginValidate.getPassword())) {
            // 登录成功，使用 Sa-Token 记录登录信息
            StpUtil.login(user.getId());

            // 获取当前用户的 token
            String token = StpUtil.getTokenValue();

            // 生成 Refresh Token
            String refreshToken = generateRefreshToken(user.getId()); // 自定义生成 Refresh Token 的方法

            // 返回 token
            SystemLoginVo systemLoginVo = new SystemLoginVo();
            systemLoginVo.setAccessToken(token);
            systemLoginVo.setRefreshToken(refreshToken);
            return systemLoginVo;
        } else {
            // 登录失败，抛出异常或返回 null
            throw new IllegalArgumentException("Login Failed: Invalid username or password");
        }
    }

    // 自定义生成 Refresh Token 的方法
    private String generateRefreshToken(Long userId) {
        // 示例：简单生成 Refresh Token，实际应用中应考虑使用 JWT 或其他方式
        return StpUtil.getTokenValue() + "-refresh-" + userId; // 示例 Refresh Token
    }

}
