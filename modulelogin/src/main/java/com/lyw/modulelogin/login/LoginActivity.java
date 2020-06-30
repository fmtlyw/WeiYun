package com.lyw.modulelogin.login;

import com.lyw.modulelogin.been.UserInfo;
import com.lyw.modulemvp.base.BaseMvpActivity;

/**
 * 功能描述:
 * Created on 2020/6/29.
 * @author lyw
 */
public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginContract.View{


    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }



    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initView() {

    }

    @Override
    public UserInfo getUserInfo() {
        return null;
    }

    @Override
    public void loginSuccess(UserInfo userInfo) {

    }
}
