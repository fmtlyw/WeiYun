package com.lyw.modulemvp.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.WindowManager;

import com.lyw.modulemvp.dialog.LoadingLayout;

/**
 * 功能描述:所有Activity的基类
 * Created on 2020/6/29.
 *
 * @author lyw
 */
public abstract class BaseActivity extends FragmentActivity {
    protected Context mContext;
    protected LoadingLayout mLoadingLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        setContentView(getLayoutId());
        mLoadingLayout = new LoadingLayout(this);
    }

    protected abstract int getLayoutId();

    protected abstract void initView();
}
