package com.soft.niuyi.mvp_master.mvp.api.rxandroid;


import com.soft.niuyi.mvp_master.base.BaseEntity;

public class HttpResultEntity<T> extends BaseEntity {

    private boolean success;
    private int resultCode;
    private String msg;
    private T data;

    public boolean isSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }

    public int getResultCode() {
        return resultCode;
    }

    public String getMsg() {
        return msg;
    }
}
