package com.yunda.base.common.exception;

public class BusinessException extends Exception {

    private String msg;
    private int code;

    public BusinessException(){

    }

    public BusinessException(String msg){

    }

    public BusinessException(String msg,int code){
        this.msg = msg;
        this.code = code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
