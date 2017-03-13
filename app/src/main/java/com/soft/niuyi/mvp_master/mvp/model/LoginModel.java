package com.soft.niuyi.mvp_master.mvp.model;

import com.soft.niuyi.mvp_master.entity.UserInfoEntity;
import com.soft.niuyi.mvp_master.mvp.api.retrofit.ApiEngine;
import com.soft.niuyi.mvp_master.mvp.contract.LoginContract;
import com.soft.niuyi.mvp_master.mvp.rx.RxSchedulers;

import rx.Observable;

/**
 * 作者：${牛毅}
 * 时间：2017/03/06 15：20
 * 邮箱：niuyi19900923@gmail.com
 * 描述：
 */
public class LoginModel implements LoginContract.Model {

    @Override
    public Observable<UserInfoEntity> loginIn(String username, String password) {
        return ApiEngine
                .getInstance()
                .getApiService()
                .login(username, password)
                .compose(RxSchedulers.<UserInfoEntity>switchThread());
    }
}
