package com.soft.niuyi.mvp_master.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Activity基类
 * 作者：${牛毅} on 2016/04/07 10:10
 * 邮箱：niuyi19900923@hotmail.com
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    protected P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (onCreatePresenter() != null) {
            mPresenter = onCreatePresenter();
        }
        View mContextView = LayoutInflater.from(this).inflate(bindLayout(), null);//设置渲染视图View
        setContentView(mContextView);
        ButterKnife.bind(this);
        initView(mContextView);//初始化控件
        setListener(this);//设置监听
        toDo(this);//处理业务逻辑
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.unSubscribe();
        }
    }

    /**
     * 绑定渲染视图的布局文件
     *
     * @return 布局文件资源id
     */
    protected abstract int bindLayout();

    /**
     * 初始化控件
     */
    protected abstract void initView(final View view);

    /**
     * 设置监听
     */
    protected abstract void setListener(Context mContext);

    /**
     * 业务处理操作
     *
     * @param mContext 当前Activity对象
     */
    protected abstract void toDo(Context mContext);

    /**
     * 创建Presenter
     * @return
     */
    protected abstract P onCreatePresenter();

}
