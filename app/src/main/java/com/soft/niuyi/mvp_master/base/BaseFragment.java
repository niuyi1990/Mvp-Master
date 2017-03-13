package com.soft.niuyi.mvp_master.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Fragment基类
 * 作者：${牛毅} on 2016/04/07 10:10
 * 邮箱：niuyi19900923@hotmail.com
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment {

    private View mContextView = null;//当前Fragment渲染的视图View

    protected P mPresenter;

    protected Context mContext;

    private boolean mIsViewPrepared; // 标识fragment视图已经初始化完毕
    private boolean mHasLoadData; // 标识已经触发过懒加载数据

    @Override
    public void onAttach(Context mContext) {
        super.onAttach(mContext);
        if (mContext != null) {
            this.mContext = mContext;
        } else {
            this.mContext = getActivity();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (onCreatePresenter() != null) {
            mPresenter = onCreatePresenter();
        }
        if (mContextView == null) mContextView = inflater.inflate(bindLayout(), container, false);
        ButterKnife.bind(this, mContextView);
        initView(mContextView);
        toDo(getActivity());//业务处理
        setListener(getActivity());//设置监听
        return mContextView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mIsViewPrepared = true;
        lazyLoadDataIfPrepared();
    }

    private void lazyLoadDataIfPrepared() {
        // 用户可见fragment && 没有加载过数据 && 视图已经准备完毕
        if (getUserVisibleHint() && !mHasLoadData && mIsViewPrepared) {
            mHasLoadData = true;
            lazyLoadData();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            lazyLoadDataIfPrepared();
        }
    }

    /**
     * 懒加载的方式获取数据，
     * 仅在满足fragment可见和视图已经准备好,并且数据没有加载的时候调用一次
     */
    protected void lazyLoadData() {

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
     * 业务处理操作（onCreateView方法中调用）
     *
     * @param mContext 当前Activity对象
     */
    protected abstract void toDo(Context mContext);

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHasLoadData = false;
        mIsViewPrepared = false;
        if (mPresenter != null) {
            mPresenter.unSubscribe();
        }
    }

    /**
     * 创建Presenter
     *
     * @return
     */
    protected abstract P onCreatePresenter();

}
