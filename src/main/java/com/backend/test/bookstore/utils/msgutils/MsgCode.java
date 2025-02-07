package com.backend.test.bookstore.utils.msgutils;


import lombok.Getter;

@Getter
public enum MsgCode {
    SUCCESS(MsgUtil.SUCCESS, MsgUtil.SUCCESS_MSG),
    ERROR(MsgUtil.ERROR,MsgUtil.ERROR_MSG),
    LOGIN_USER_ERROR(MsgUtil.LOGIN_USER_ERROR,MsgUtil.LOGIN_USER_ERROR_MSG),
    NOT_LOGGED_IN_ERROR(MsgUtil.NOT_LOGGED_IN_ERROR,MsgUtil.NOT_LOGGED_IN_ERROR_MSG);

    private int status;
    private String msg;

    private MsgCode(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
