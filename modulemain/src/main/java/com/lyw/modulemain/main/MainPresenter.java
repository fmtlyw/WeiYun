package com.lyw.modulemain.main;

import com.lyw.modulemvp.base.BasePresenter;

/**
 * 功能描述:
 * Created on 2020/9/9.
 *
 * @author lyw
 */
public class MainPresenter extends BasePresenter<MainContract.Model,MainContract.View> implements MainContract.Presenter{
    @Override
    protected MainModel createModule() {
        return new MainModel();
    }

    @Override
    public void start() {

    }
}
