package com.example.enums;

/**
 * @author xuan
 * @create 2018-06-07 15:04
 **/
public enum RoleEnum {
    BUYER(1, "买家"),
    SELLER(2, "卖家");

    private int code;

    private String msg;

    RoleEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {

        return code;
    }
}
