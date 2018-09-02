package com.yunda.base.common.exception;

import org.apache.shiro.authc.AuthenticationException;

public class SyAuthException extends AuthenticationException {

    private String msg;
    private int code;


    public SyAuthException(){

    }

    public SyAuthException(String msg){
        this.msg = msg;
    }

    public SyAuthException(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }
}
