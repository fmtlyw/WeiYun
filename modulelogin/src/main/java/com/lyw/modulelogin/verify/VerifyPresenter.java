package com.lyw.modulelogin.verify;

import com.lyw.modulemvp.base.BasePresenter;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

/**
 * 功能描述:
 * Created on 2020/7/6.
 *
 * @author lyw
 */
public class VerifyPresenter extends BasePresenter<VerifyContract.Model,VerifyContract.View> implements VerifyContract.Presenter {

    @Override
    protected VerifyContract.Model createModule() {
        return new VerifyModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void sendVerifyCode(final String userAcount) {
            BmobSMS.requestSMSCode(userAcount, "微云", new QueryListener<Integer>() {
                @Override
                public void done(Integer smsId, BmobException e) {
                    if (e == null) {
                        getView().getVerifyCode(userAcount,smsId);
                    } else {
                        getView().showErrorTisps("发送验证码失败:"+e.getErrorCode() + e.getMessage());
                    }
                }
            });
    }
}
