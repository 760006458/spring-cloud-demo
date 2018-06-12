package com.example.enums;

/**
 * @author xuan
 * @create 2018-06-07 14:18
 **/
public enum ResultEnum {
    LOGIN_FAIL(1, "登录失败"),
    ROLE_ERROR(2, "角色错误"),;

    private int code;

    private String msg;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
