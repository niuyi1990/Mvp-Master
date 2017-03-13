package com.soft.niuyi.mvp_master.mvp.api.rxandroid;

import com.soft.niuyi.mvp_master.mvp.api.exception.APIException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

public abstract class HttpResultSubscriber<T> extends Subscriber<T> {

    @Override
    public void onStart() {
        super.onStart();
        onBegin();
    }

    @Override
    public void onCompleted() {
        onFinish();
    }

    @Override
    public void onError(Throwable e) {
        String errormsg;
        if (e instanceof APIException) {
            APIException exception = (APIException) e;
            errormsg = exception.message;
        } else if (e instanceof UnknownHostException) {
            errormsg = "请打开网络";
        } else if (e instanceof SocketTimeoutException) {
            errormsg = "请求超时";
        } else if (e instanceof ConnectException) {
            errormsg = "连接失败";
        } else if (e instanceof HttpException) {
            errormsg = "请求超时";
        } else {
            errormsg = "请求失败";
        }
        errorMessage(errormsg);
        e.printStackTrace();
    }

    @Override
    public void onNext(T tBaseResponse) {
        if (tBaseResponse != null) {
            onSuccess(tBaseResponse);
        } else {
            errorMessage("连接失败");
        }
    }

    public void errorMessage(String errormsg) {
        onError(errormsg);
    }

    public abstract void onBegin();

    public abstract void onSuccess(T t);

    public abstract void onError(String error);

    public abstract void onFinish();
}
