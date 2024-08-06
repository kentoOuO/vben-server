package com.example.vben_server.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import com.example.vben_server.annotation.NotLogin;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;


public class CustomSaInterceptor extends SaInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 检查 handler 是否为 HandlerMethod 类型
        if (handler instanceof HandlerMethod handlerMethod) {

            // 检查方法是否有 @NotLogin 注解
            if (handlerMethod.hasMethodAnnotation(NotLogin.class) ||
                    handlerMethod.getBeanType().isAnnotationPresent(NotLogin.class)) {
                return true; // 不需要登录，继续执行
            }

            // 检查登录
            StpUtil.checkLogin(); // 如果未登录，则抛出异常
        } else {
            // 如果是 ResourceHttpRequestHandler 等类型，直接返回 true
            return true; // 让请求继续处理
        }

        return true; // 登录检查通过
    }
}
