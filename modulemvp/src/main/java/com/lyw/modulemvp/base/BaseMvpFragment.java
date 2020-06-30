package com.lyw.modulemvp.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * 功能描述:Mvp Fragment基类
 * Created on 2020/6/29.
 * @author lyw
 */
public abstract class BaseMvpFragment <P extends BasePresenter> extends BaseFragment implements IBaseView {
    protected P presenter;
    private Context mContext;

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建present
        presenter = createPresenter();
        if (presenter != null) {
            presenter.attachView(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
            presenter = null;
        }

    }
    @Override
    public void showLoading() {
    }

    @Override
    public void dismissLoading() {
    }

    @Override
    public void onEmpty(Object tag) {

    }

    @Override
    public void onError(Object tag, String errorMsg) {

    }

    @Override
    public Context getContext() {
        return mContext;
    }
    /**
     * 创建Presenter
     */
    protected abstract P createPresenter();
}
