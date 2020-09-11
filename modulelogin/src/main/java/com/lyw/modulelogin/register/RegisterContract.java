package com.lyw.modulelogin.register;

import com.lyw.modulemvp.base.IBaseModel;
import com.lyw.modulemvp.base.IBaseView;

/**
 * 功能描述:
 * Created on 2020/7/2.
 *
 * @author lyw
 */
public interface RegisterContract {
    interface Model extends IBaseModel{

    }

    interface View extends IBaseView{
        /**
         *提示
         * @param tips
         */
        void handleCheckResult(String tips);

        /**
         * 确认之后的显示
         * @param userAccountEdt
         */
        void showUserAccountEdt(String userAccountEdt);

        /**
         * 设置mIsCheckNumber
         * @param mIsCheckNumber
         */
        void setIsCheckNumber(boolean mIsCheckNumber);

        /**
         * 是否显示加载框
         * @param isShowLoading
         */
        void isShowLoading(boolean isShowLoading);

        /**
         * 进入验证码验证界面
         * @param userAccount    输入的账号
         */
        void goinVerifyCode(String userAccount);
    }

    interface Presenter{
        /**
         * 检查账号
         * @param mIsCheckNumber 是否检查了账号
         * @param userAccount    输入的账号
         */
        void checkAccount(boolean mIsCheckNumber,String userAccount);
    }
}
