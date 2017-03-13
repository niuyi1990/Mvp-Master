package com.soft.niuyi.mvp_master.mvp.contract;

import com.soft.niuyi.mvp_master.base.BaseModel;
import com.soft.niuyi.mvp_master.base.BasePresenter;
import com.soft.niuyi.mvp_master.base.BaseView;
import com.soft.niuyi.mvp_master.entity.UserInfoEntity;

import rx.Observable;

/**
 * 作者：${牛毅}
 * 时间：2017/03/06 15：16
 * 邮箱：niuyi19900923@gmail.com
 * 描述：
 */
public interface LoginContract {

    interface View extends BaseView {
        void showLoadingDialog();

        void hideLoadingDialog();

        void onLoginSucceed(UserInfoEntity userInfoEntity);

        void onLoginFailed(String err);
    }

    interface Model extends BaseModel {
        Observable<UserInfoEntity> loginIn(String username, String password);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void loginIn(String username, String password);
    }
}
