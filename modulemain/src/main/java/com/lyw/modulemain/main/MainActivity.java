package com.lyw.modulemain.main;

import com.chenenyu.router.annotation.Route;
import com.lyw.modulemain.R;
import com.lyw.modulemvp.base.BaseMvpActivity;

/**
 * 功能描述:主界面
 * Created on 2020/9/9.
 *
 * @author lyw
 */
@Route("com.lyw.modulemain.main.MainActivity")
public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.View{
    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_activity;
    }

    @Override
    protected void initView() {

    }
}
