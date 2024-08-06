package com.example.vben_server.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AjaxResult<T> {
    private int code;
    private String message;
    private T result;

    public AjaxResult() {
    }

    public AjaxResult(int code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public static <T> AjaxResult<T> success(T result) {
        return new AjaxResult<>(0, "成功", result);
    }

    public static <T> AjaxResult<T> success(String message, T result) {
        return new AjaxResult<>(0, message, result);
    }

    public static <T> AjaxResult<T> success() {
        return new AjaxResult<>(0, "成功",null);
    }

    public static <T> AjaxResult<T> error(String message) {
        return new AjaxResult<>(500, message, null);
    }

    public static <T> AjaxResult<T> error(int code, String message) {
        return new AjaxResult<>(code, message, null);
    }

    // Getters and Setters

}