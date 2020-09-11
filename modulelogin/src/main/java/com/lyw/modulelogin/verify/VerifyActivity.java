package com.lyw.modulelogin.verify;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chenenyu.router.annotation.Route;
import com.lyw.commonutils.utils.StatusBarUtil;
import com.lyw.modulelogin.R;
import com.lyw.modulelogin.R2;
import com.lyw.modulemvp.base.BaseMvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 功能描述:验证（手机号/邮箱）
 * Created on 2020/7/6.
 *
 * @author lyw
 */
@Route("com.lyw.modulelogin.verify.VerifyActivity")
public class VerifyActivity extends BaseMvpActivity<VerifyPresenter> implements VerifyContract.View {
    public static final String BUNDLE_KEY_USER_NAME = "user_name";

    @BindView(R2.id.title_tv)
    TextView mTitleTv;
    @BindView(R2.id.content_tv)
    TextView mContentTv;
    @BindView(R2.id.verify_code_edt)
    EditText mVerifyCodeEdt;
    @BindView(R2.id.password_edt)
    EditText mPwdEdt;
    @BindView(R2.id.ensure_password_edt)
    EditText mEnsurePwdEdt;
    @BindView(R2.id.verify_code_iv)
    ImageView mVerifyCodeIv;
    @BindView(R2.id.tips_ll)
    LinearLayout mTipsLl;
    @BindView(R2.id.tips_tv)
    TextView mTipsTv;
    @BindView(R2.id.password_iv)
    ImageView mPwdIv;
    @BindView(R2.id.ensure_password_iv)
    ImageView mEnsurePwdIv;
    @BindView(R2.id.pwd_tips_ll)
    LinearLayout mPwdTipsLl;
    @BindView(R2.id.pwd_tips_tv)
    TextView mPwdTipsTv;
    @BindView(R2.id.confirm_btn)
    Button mConfirmBtn;
    @BindView(R2.id.verify_code_tv)
    TextView mReGetVerifyCodeTv;
    @BindView(R2.id.verify_code_line)
    View mReGetVerifyCodeLine;
    @BindView(R2.id.set_pwd_tip_tv)
    TextView mSetPwdTipTv;
    @BindView(R2.id.now_login_tv)
    TextView mNowLoginTv;
    @BindView(R2.id.now_login_view)
    View mNowLoginView;
    @BindView(R2.id.password_eye_iv)
    ImageView mPasswordEyeIv;
    @BindView(R2.id.ensure_password_eye_iv)
    ImageView mEnsurePasswordEyeIv;

    private String userAcount;

    @Override
    protected void init() {
        super.init();
        //全屏
        StatusBarUtil.setNoSystemStatusBar(this);
    }

    @Override
    protected VerifyPresenter createPresenter() {
        return new VerifyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_verify_code;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        userAcount = bundle.getString(BUNDLE_KEY_USER_NAME);

        presenter.sendVerifyCode(userAcount);

    }

    @Override
    public void getVerifyCode(String userAcount,int smsId) {
        mContentTv.setText("验证码已发送至" + userAcount + ", 请注意查收。");
    }

    @Override
    public void showErrorTisps(String errorTips) {
        Toast.makeText(this,errorTips,Toast.LENGTH_SHORT).show();
    }


    @OnClick(R2.id.verify_code_ll)
    void onClickVerifyCodeAgain(View view) {
        presenter.sendVerifyCode(userAcount);
    }

    @OnClick(R2.id.back_fl)
    void onClickBack(View view) {
        finish();
    }
}
