package com.lyw.modulelogin.login;
import com.lyw.modulemvp.base.BasePresenter;
/**
 * 功能描述:
 * Created on 2020/6/29.
 * @author lyw
 */
public class LoginPresenter extends BasePresenter<LoginContract.Model, LoginContract.View> implements LoginContract.Presenter{

    @Override
    protected LoginModel createModule() {
        return new LoginModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void login() {

    }
}
