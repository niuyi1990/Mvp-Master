package com.soft.niuyi.mvp_master;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.soft.niuyi.mvp_master.base.BaseActivity;
import com.soft.niuyi.mvp_master.entity.UserInfoEntity;
import com.soft.niuyi.mvp_master.mvp.contract.LoginContract;
import com.soft.niuyi.mvp_master.mvp.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.btn_login)
    Button mBtnLogin;

    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void hasNetwork(boolean b) {
        Toast.makeText(this, "------" + b, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void setListener(Context mContext) {

    }

    @Override
    protected void toDo(Context mContext) {

    }

    @Override
    protected LoginPresenter onCreatePresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void hideLoadingDialog() {

    }

    @Override
    public void onLoginSucceed(UserInfoEntity userInfoEntity) {
        Log.e("userInfoEntity===", "---" + userInfoEntity);
    }

    @Override
    public void onLoginFailed(String err) {
        Toast.makeText(this, err, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_login)
    public void onClick() {
        mPresenter.loginIn("toutiao", "c0a832d7e26bc6a5966ea6bf2e0709c7");
    }
}
