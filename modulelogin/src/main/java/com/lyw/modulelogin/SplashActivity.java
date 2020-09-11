package com.lyw.modulelogin;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import com.chenenyu.router.Router;
import com.lyw.modulemvp.base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 功能描述:闪屏页
 * Created on 2020/6/30.
 * @author lyw
 */
public class SplashActivity extends BaseActivity {
    @BindView(R2.id.skip_tv)
    TextView mSkipTv;
    private boolean mIsClickSkip;

    private MyCountDown mCountDown;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        //防止启动两个activity
        if (!isTaskRoot()) {
            finish();
            return;
        }
        ButterKnife.bind(this);

        //倒计时
        mCountDown = new MyCountDown(5000, 1000);
        mCountDown.start();
    }

    @OnClick(R2.id.skip_tv)
    void onClickSkip(View view) {
        if (mCountDown != null) {
            mCountDown.cancel();
        }
        mIsClickSkip = true;
        startAct(null);
    }

    class MyCountDown extends CountDownTimer {
        public MyCountDown(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
//            if (!TextUtils.isEmpty(mADSkipUrl)) {
                int second = Math.round(l / 1000f);
                mSkipTv.setText(String.format("%d " + "跳过", second));
//            }
        }

        @Override
        public void onFinish() {
            if (!mIsClickSkip) {
                mIsClickSkip = true;
                startAct(null);
            }
        }
    }

    private void startAct(String skipUrl) {
        Router.build("com.lyw.modulelogin.login.LoginActivity").go(this);
        SplashActivity.this.finish();
    }
}
