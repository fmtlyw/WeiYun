package com.lyw.modulelogin.register;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chenenyu.router.Router;
import com.chenenyu.router.annotation.Route;
import com.lyw.commonutils.dialog.LoadingDialog;
import com.lyw.commonutils.utils.StatusBarUtil;
import com.lyw.modulelogin.R;
import com.lyw.modulelogin.R2;
import com.lyw.modulelogin.verify.VerifyActivity;
import com.lyw.modulemvp.base.BaseMvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 功能描述:注册activity
 * Created on 2020/7/2.
 * @author lyw
 */
@Route("com.lyw.modulelogin.register.RegisterActivity")
public class RegisterActivity extends BaseMvpActivity<RegisterPresenter> implements RegisterContract.View{
    private static final int GET_VERIFY_SUCCESS = 0;

    @BindView(R2.id.title_tv)
    TextView mTitleTv;
    @BindView(R2.id.confirm_btn)
    Button mRegisterBtn;
    @BindView(R2.id.user_name_edt)
    EditText mUserNameEdt;
    @BindView(R2.id.check_tip_iv)
    ImageView mCheckTipIv;
    @BindView(R2.id.tips_ll)
    LinearLayout mTipsLl;
    @BindView(R2.id.tips_tv)
    TextView mTipsTv;
    @BindView(R2.id.now_login_ll)
    LinearLayout mLoginNowLl;
    @BindView(R2.id.user_protocol_ll)
    LinearLayout mUserProtocolLl;
    @BindView(R2.id.delete_user_fl)
    FrameLayout mDeleteFl;
    @BindView(R2.id.user_protocol_tv)
    TextView mUserProtocolTv;
    @BindView(R2.id.user_protocol_iv)
    ImageView mUserProtocolIv;
    @BindView(R2.id.new_login_tv)
    TextView mNewLoginTv;
    @BindView(R2.id.register_content_ll)
    LinearLayout mRegisterContentLl;
    @BindView(R2.id.register_bottom_ll)
    LinearLayout mRegisterBottomLl;

    /**
     * 是否检查了账号
     */
    private boolean mIsCheckNumber;
    private RotateAnimation mAnimation;
    private String mCurrentName;

    private  Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == GET_VERIFY_SUCCESS) {
                mCheckTipIv.clearAnimation();
                mCheckTipIv.setImageResource(R.mipmap.icon_login_agree);
                try {
                    mLoadingDialog.show();
                } catch (WindowManager.BadTokenException e) {
                    e.printStackTrace();
                }

                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mLoadingDialog.dismiss();
                        Bundle bundle = new Bundle();
                        bundle.putString(VerifyActivity.BUNDLE_KEY_USER_NAME, mCurrentName);
                        Router.build("com.lyw.modulelogin.verify.VerifyActivity")
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).with(bundle).go(RegisterActivity.this);
                    }
                }, 2000);
            }
        }
    };

    @Override
    protected RegisterPresenter createPresenter() {
        return new RegisterPresenter(this);
    }

    @Override
    protected void init() {
        super.init();
        //全屏
        StatusBarUtil.setNoSystemStatusBar(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        //输入框进行监听
        mUserNameEdt.addTextChangedListener(new MyEditWatcherListener());
        initAnimation();
    }

    /**
     * 初始化加载框
     */
    private void initAnimation(){
        mAnimation = new RotateAnimation(0, 359, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mAnimation.setDuration(1000);
        mAnimation.setRepeatCount(-1);
        mAnimation.setInterpolator(new LinearInterpolator());
    }


    @OnClick(R2.id.confirm_btn)
    void onClickRegister(View view) {
        String mUserAccount = mUserNameEdt.getText().toString();
        presenter.checkAccount(mIsCheckNumber,mUserAccount);
    }

    @OnClick(R2.id.back_fl)
    void onClickBack(View view) {
        finish();
    }

    @OnClick(R2.id.delete_user_fl)
    void onClickDelete(View view) {
        mUserNameEdt.setText("");
    }

    @Override
    public void handleCheckResult(String tips) {
        Toast.makeText(this,tips,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUserAccountEdt(String userAccountEdt) {
        mUserNameEdt.setText(userAccountEdt);
        mUserNameEdt.setSelection(mUserNameEdt.getText().length());
    }

    @Override
    public void setIsCheckNumber(boolean mIsCheckNumber) {
        this.mIsCheckNumber = mIsCheckNumber;
    }

    @Override
    public void isShowLoading(boolean isShowLoading) {
        if (isShowLoading) {
            mTipsLl.setVisibility(View.INVISIBLE);
            mCheckTipIv.setImageResource(R.mipmap.icon_login_loading);
            mCheckTipIv.setAnimation(mAnimation);
            mCheckTipIv.getAnimation().start();
            mCheckTipIv.setVisibility(View.VISIBLE);
        }else {
            mCheckTipIv.clearAnimation();
            mCheckTipIv.setImageResource(R.mipmap.icon_login_agree);
        }
    }

    @Override
    public void goinVerifyCode(String userAccount) {
        mCurrentName = userAccount;
        mHandler.sendEmptyMessage(GET_VERIFY_SUCCESS);
    }

    /**
     * 监听输入框 按钮变化
     */
    private class MyEditWatcherListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            mIsCheckNumber = false;
            String username = mUserNameEdt.getText().toString();
            if (!TextUtils.isEmpty(username) && username.length() < 100) {
                mRegisterBtn.setAlpha(1.0f);
                mDeleteFl.setVisibility(View.VISIBLE);
            } else {
                mRegisterBtn.setAlpha(0.3f);
                mDeleteFl.setVisibility(View.GONE);
            }
        }
    }
}
