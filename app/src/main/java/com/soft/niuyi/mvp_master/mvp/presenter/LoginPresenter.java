package com.soft.niuyi.mvp_master.mvp.presenter;


import com.soft.niuyi.mvp_master.entity.UserInfoEntity;
import com.soft.niuyi.mvp_master.mvp.api.rxandroid.HttpResultSubscriber;
import com.soft.niuyi.mvp_master.mvp.contract.LoginContract;
import com.soft.niuyi.mvp_master.mvp.model.LoginModel;

import rx.Subscription;

/**
 * 作者：${牛毅}
 * 时间：2017/03/06 15：20
 * 邮箱：niuyi19900923@gmail.com
 * 描述：
 */
public class LoginPresenter extends LoginContract.Presenter {

    public LoginPresenter(LoginContract.View view) {
        mView = view;
        mModel = new LoginModel();
    }

    @Override
    public void loginIn(String username, String password) {
        Subscription subscription = mModel.loginIn(username, password)
                .subscribe(new HttpResultSubscriber<UserInfoEntity>() {
                    @Override
                    public void onBegin() {
                        mView.showLoadingDialog();
                    }

                    @Override
                    public void onSuccess(UserInfoEntity userInfoEntity) {
                        //这是网络请求成功的回调
                        mView.onLoginSucceed(userInfoEntity);
                    }

                    @Override
                    public void onError(String error) {
                        mView.onLoginFailed(error);
                    }

                    @Override
                    public void onFinish() {
                        mView.hideLoadingDialog();
                    }

                });

        addSubscribe(subscription);

    }
}
