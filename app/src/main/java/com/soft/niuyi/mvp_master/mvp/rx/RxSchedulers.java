package com.soft.niuyi.mvp_master.mvp.rx;

import com.soft.niuyi.mvp_master.mvp.api.rxandroid.HttpResultEntity;
import com.soft.niuyi.mvp_master.mvp.api.exception.APIException;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Rxjava线程管理切换类
 * 作者：${牛毅} on 2016/11/24 10:18
 * 邮箱：niuyi19900923@hotmail.com
 */
public class RxSchedulers {

    public static <T> Observable.Transformer<HttpResultEntity<T>, T> switchThread() {
        return new Observable.Transformer<HttpResultEntity<T>, T>() {
            @Override
            public Observable<T> call(Observable<HttpResultEntity<T>> httpResultEntityObservable) {
                return httpResultEntityObservable
                        .subscribeOn(Schedulers.io()) //在IO线程进行网络请求
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .timeout(5, TimeUnit.SECONDS)
                        .retry(5)
                        .flatMap(new Func1<HttpResultEntity<T>, Observable<T>>() {
                            @Override
                            public Observable<T> call(HttpResultEntity<T> tBaseResponse) {
                                return flatResponse(tBaseResponse);
                            }
                        });
            }
        };
    }

    /**
     * 对网络接口返回的Response进行分割操作
     *
     * @param response HttpResultEntity
     * @return Observable
     */
    private static <T> Observable<T> flatResponse(final HttpResultEntity<T> response) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                if (response.isSuccess()) {//请求成功
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onNext(response.getData());
                    }
                } else {//请求失败
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onError(new APIException(response.getResultCode(), response.getMsg()));
                    }
                    return;
                }
                if (!subscriber.isUnsubscribed()) {//请求完成
                    subscriber.onCompleted();
                }
            }
        });
    }

}
