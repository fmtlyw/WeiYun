package com.lyw.modulelogin.login;
import com.lyw.modulelogin.been.UserInfo;
import com.lyw.modulemvp.base.IBaseModel;
import com.lyw.modulemvp.base.IBaseView;
import com.lyw.moudlehttp.http.response.ResponseListener;

/**
 * 功能描述:
 * Created on 2020/6/29.
 * @author lyw
 */
public interface LoginContract {
    interface Model extends IBaseModel{
        /**
         * 登录
         *
         * @param userInfo     用户信息
         * @param callback 回调
         */
        void login(UserInfo userInfo, ResponseListener callback);
    }

    interface View extends IBaseView {
        /**
         * 返回用户信息
         */
        UserInfo getUserInfo();
        /**
         * 登录成功
         */
        void loginSuccess(UserInfo userInfo);
    }

    interface Presenter  {

        /**
         * 登录
         */
       void login();
    }
}
