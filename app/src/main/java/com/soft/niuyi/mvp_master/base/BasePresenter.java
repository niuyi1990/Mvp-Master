package com.soft.niuyi.mvp_master.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * 作者：${牛毅} on 2016/11/23 15:45
 * 邮箱：niuyi19900923@hotmail.com
 */
public class BasePresenter<V, M> {

    protected V mView;
    protected M mModel;

    private CompositeSubscription mCompositeSubscription;

    protected void addSubscribe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    public void unSubscribe() {
        if (mView != null) {
            mView = null;
        }
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.clear();
        }
    }

}
