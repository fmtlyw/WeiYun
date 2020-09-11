package com.lyw.modulelogin.verify;

import com.lyw.modulemvp.base.IBaseModel;
import com.lyw.modulemvp.base.IBaseView;

/**
 * 功能描述:
 * Created on 2020/7/6.
 *
 * @author lyw
 */
public interface VerifyContract {
    interface Model extends IBaseModel{

    }

    interface View extends IBaseView{
        /**
         * 获取验证码
         * @param smsId
         */
        void getVerifyCode(String userAcount,int smsId);

        /**
         * 显示错误信息
         * @param errorTips
         */
        void showErrorTisps(String errorTips);
    }

    interface Presenter{
        /**
         * 发送验证码
         */
        void sendVerifyCode(String userAcount);
    }
}
